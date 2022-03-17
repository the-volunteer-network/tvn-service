package edu.cnm.deepdive.tvnservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.service.AbstractOpportunityService;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations/{organizationId}/opportunities")
public class OpportunityController {

  private final AbstractOpportunityService opportunityService;
  private final AbstractUserService userService;
  private final ObjectMapper mapper;

  public OpportunityController(
      AbstractOpportunityService opportunityService,
      AbstractUserService userService, ObjectMapper mapper) {
    this.opportunityService = opportunityService;
    this.userService = userService;
    this.mapper = mapper;
  }

  @GetMapping(value = "/{opportunityId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Opportunity get(@PathVariable UUID organizationId, @PathVariable UUID opportunityId) {
    return opportunityService
        .getOpportunity(organizationId, opportunityId, userService.getCurrentUser())
        .orElseThrow();
  }

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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Opportunity> getAll(@PathVariable UUID organizationId){
    return opportunityService.getAllOpportunities(organizationId);
  }

  @DeleteMapping(value = "/{opportunityId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID organizationId,@PathVariable UUID opportunityId){
    opportunityService.deleteOpportunity(organizationId, opportunityId, userService.getCurrentUser());
  }
}
