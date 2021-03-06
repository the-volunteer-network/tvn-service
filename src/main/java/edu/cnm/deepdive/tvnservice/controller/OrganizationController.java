package edu.cnm.deepdive.tvnservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import edu.cnm.deepdive.tvnservice.service.AbstractOrganizationService;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import edu.cnm.deepdive.tvnservice.service.UserService;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import edu.cnm.deepdive.tvnservice.service.OrganizationService;

/**
 * Handles REST requests for operation on individual instances and collections of {@link  Organization} entity.
 */
@RestController
@RequestMapping(PathComponents.ORGANIZATIONS)
public class OrganizationController {

  private final AbstractOrganizationService organizationService;
  private final AbstractUserService userService;
  private final ObjectMapper mapper;

  /**
   * Initializes this instance with {@link OrganizationService} &amp; {@link UserService},instances
   * used to perform the requested operations.
   *
   * @param userService         provides access to high-level query &amp; persistence operations on
   *                            {@link User} instances.
   * @param organizationService provides persistence operations on {@link Organization}.
   * @param mapper  provides functionality for reading and writing JSON.
   */

  public OrganizationController(
      AbstractOrganizationService organizationService,
      AbstractUserService userService, ObjectMapper mapper) {
    this.organizationService = organizationService;
    this.userService = userService;
    this.mapper = mapper;
  }

  /**
   * Selects and Returns a specified {@link Organization}.
   *
   * @param organizationId a unique identifier {@link Organization} resource.
   * @return specified {@link Organization}
   */
  @GetMapping(value = PathComponents.SPECIFIC_ORGANIZATION, produces = MediaType.APPLICATION_JSON_VALUE)
  public Organization get(@PathVariable UUID organizationId) {
    return organizationService
        .getOrganization(organizationId,userService.getCurrentUser())
        .orElseThrow();
  }


  /**
   * Adds an {@link Organization} to the service.
   *
   * @param organization {@link Organization} to be added to the database table.
   * @return a ResponseEntity of successful creation.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Organization> post(@Valid @RequestBody Organization organization) {
    Organization created =
        organizationService.addOrganization(organization, userService.getCurrentUser());
    URI location = WebMvcLinkBuilder
        .linkTo(
            WebMvcLinkBuilder
                .methodOn(OrganizationController.class)
                .get(created.getExternalKey())
        )
        .toUri();
    return ResponseEntity
        .created(location)
        .body(created);
  }

  /**
   * Deletes a specified {@link Organization}from the Database.
   * @param organizationId  a unique identifier {@link Organization} resource.
   */
  @DeleteMapping(value = PathComponents.SPECIFIC_ORGANIZATION)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID organizationId) {
    organizationService.deleteOrganization(organizationId, userService.getCurrentUser());
  }

  /**
   * Modifies a specified {@link Organization}
   * @param organizationId a unique identifier {@link Organization} resource.
   * @param organization an instance of the {@link Organization} entity
   * @return the modified {@link Organization} to the Database.
   */
  @PutMapping(value = PathComponents.SPECIFIC_ORGANIZATION, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Organization modify(@PathVariable UUID organizationId,
      @RequestBody @Valid Organization organization) {
    return organizationService
        .modifyOrganization(organizationId, organization, userService.getCurrentUser())
        .orElseThrow();
  }

  /**
   *  Passes a name to request a specified {@link Organization}.
   * @param organizationId a unique identifier {@link Organization} resource.
   * @return
   */
  @GetMapping(value = PathComponents.ORGANIZATION_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
  public String getName(@PathVariable UUID organizationId) {
    return organizationService
        .getName(organizationId)
        .map((name) -> {
          try {
            return mapper.writeValueAsString(name);
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
        })
        .orElseThrow();
  }

  /**
   * Adds the name of an {@link Organization} to the Database.
   * @param organizationId a unique identifier {@link Organization} resource.
   * @param name to be added to the database
   * @return
   * @throws JsonProcessingException exception to catch, throw a RunTimeException.
   */
  @PutMapping(value = PathComponents.ORGANIZATION_NAME, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public String putName(@PathVariable UUID organizationId,
      @RequestBody @NotNull @Pattern(regexp = "^\\s*\".*?\\S.*\"\\s*$") String name)
      throws JsonProcessingException {
    String unquotedName = mapper.readValue(name, String.class);
    return organizationService
        .modifyName(organizationId, unquotedName, userService.getCurrentUser())
        .map((updatedName) -> {
          try {
            return mapper.writeValueAsString(updatedName);
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
        })
        .orElseThrow();
  }

  /**
   *  Retrieves all of the {@link Organization} in the Database.
   * @return all of the  {@link Organization}
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Organization> getAll() {
    return organizationService.getAll(userService.getCurrentUser());
  }

  /**
   *  Searches &amp; retrieves a specified {@link Organization}
   * @param fragment part of the String passed in the search to retrieve a specified {@link Organization}
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"q"})
  public Iterable<Organization> search(@RequestParam(name = "q") @Size(min = 2) String fragment) {
    return organizationService.searchByName(fragment, userService.getCurrentUser());
  }


}
