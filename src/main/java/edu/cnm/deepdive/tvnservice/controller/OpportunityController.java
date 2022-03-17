package edu.cnm.deepdive.tvnservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.service.AbstractOpportunityService;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
