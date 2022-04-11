package edu.cnm.deepdive.tvnservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.service.AbstractOpportunityService;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import edu.cnm.deepdive.tvnservice.service.OrganizationService;
import edu.cnm.deepdive.tvnservice.service.UserService;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
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
import edu.cnm.deepdive.tvnservice.model.entity.User;

/**
 * Handles REST requests for operation on individual instances and collections of {@link
 * Opportunity} entity.
 */
@RestController
@RequestMapping("/organizations/{organizationId}/opportunities")
public class OpportunityController {

  private final AbstractOpportunityService opportunityService;
  private final AbstractUserService userService;
  private final ObjectMapper mapper;

  /**
   * Initializes this instance with {@link OrganizationService} &amp; {@link UserService},instances
   * used to perform the requested operations.
   *
   * @param opportunityService provides access to high-level query &amp; persistence operations on
   *                           {@link Opportunity} instances.
   * @param userService        provides persistence operations on {@link User}.
   * @param mapper             provides functionality for reading and writing JSON
   */
  public OpportunityController(
      AbstractOpportunityService opportunityService,
      AbstractUserService userService, ObjectMapper mapper) {
    this.opportunityService = opportunityService;
    this.userService = userService;
    this.mapper = mapper;
  }

  /**
   * Selects and returns a specified {@link Opportunity}.
   *
   * @param organizationId a unique identifier {@link Opportunity} resource.
   * @param opportunityId  a unique identifier {@link Opportunity} resource.
   * @return specified {@link Opportunity}
   */
  @GetMapping(value = "/{opportunityId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Opportunity get(@PathVariable UUID organizationId, @PathVariable UUID opportunityId) {
    return opportunityService
        .getOpportunity(organizationId, opportunityId, userService.getCurrentUser())
        .orElseThrow();
  }

  /**
   * Adds an {@link Opportunity} to the service.
   *
   * @param organizationId {a unique identifier {@link Organization} resource.
   * @param opportunity    {@link Opportunity} to be added to the database table
   * @return a ResponseEntity of successful creation.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Opportunity> post(@PathVariable UUID organizationId,
      @RequestBody @Valid Opportunity opportunity) {
    Opportunity created = opportunityService.addOpportunity(opportunity, organizationId,
        userService.getCurrentUser());
    URI location = WebMvcLinkBuilder
        .linkTo(
            WebMvcLinkBuilder
                .methodOn(OpportunityController.class)
                .get(organizationId, created.getExternalKey())
        )
        .toUri();
    return ResponseEntity
        .created(location)
        .body(created);

  }

  /**
   * Passes a name to request a specified {@link Opportunity}.
   *
   * @param organizationId unique identifier {@link Organization} resource.
   * @return the specified instance of {@link Opportunity}
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Opportunity> getAll(@PathVariable UUID organizationId) {
    return opportunityService.getAllOpportunities(organizationId);
  }

  /**
   * Deletes a specified {@link Opportunity}from the Database.
   *
   * @param organizationId a unique identifier {@link Organization} resource.
   * @param opportunityId  a unique identifier {@link Opportunity} resource.
   */
  @DeleteMapping(value = "/{opportunityId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID organizationId, @PathVariable UUID opportunityId) {
    opportunityService.deleteOpportunity(organizationId, opportunityId,
        userService.getCurrentUser());
  }

  /**
   * Modify a specified {@link Organization}
   *
   * @param organizationId a unique identifier {@link Organization} resource.
   * @param opportunityId  a unique identifier {@link Opportunity} resource.
   * @param opportunity    the specified instance of {@link Opportunity} to be modified.
   * @return the specified modified {@link Opportunity}
   */
  @PutMapping(value = "/{opportunityId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Opportunity put(@PathVariable UUID organizationId, @PathVariable UUID opportunityId,
      @RequestBody Opportunity opportunity) {
    return opportunityService
        .modifyOpportunity(organizationId, opportunityId, opportunity, userService.getCurrentUser())
        .orElseThrow();

  }

  /**
   *  Searches and retrieves a specified {@link Opportunity}
   * @param fragment part of the String passed in the search to retrieve a specified {@link Organization}
   * @param organizationId  id of the specified {@link Organization}
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"q"})
  public Iterable<Opportunity> search(@RequestParam(name = "q") @Size(min = 2) String fragment,
      @PathVariable UUID organizationId) {
    return opportunityService.searchByNameAndOrganization(fragment, organizationId);
  }



}
