package edu.cnm.deepdive.tvnservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import edu.cnm.deepdive.tvnservice.service.AbstractOpportunityService;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import edu.cnm.deepdive.tvnservice.service.OrganizationService;
import edu.cnm.deepdive.tvnservice.service.UserService;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
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

/**
 * Handles REST requests for operation on individual instances and collections of {@link
 * Opportunity} entity.
 */
@RestController
@RequestMapping(PathComponents.OPPORTUNITIES_SEARCH)
public class OpportunitySearchController {

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
  public OpportunitySearchController(
      AbstractOpportunityService opportunityService,
      AbstractUserService userService, ObjectMapper mapper) {
    this.opportunityService = opportunityService;
    this.userService = userService;
    this.mapper = mapper;
  }


  /**
   *  Searches and retrieves a specified {@link Opportunity}
   * @param fragment part of the String passed in the search to retrieve a specified {@link Organization}
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"q"})
  public Iterable<Opportunity> search(@RequestParam(name = "q") @Size(min = 2) String fragment) {
    return opportunityService.searchByName(fragment);
  }



}
