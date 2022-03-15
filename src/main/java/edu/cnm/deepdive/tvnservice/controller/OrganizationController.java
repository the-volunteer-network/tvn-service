package edu.cnm.deepdive.tvnservice.controller;

import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.service.AbstractOrganizationService;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

  private final AbstractOrganizationService organizationService;
  private final AbstractUserService userService;

  public OrganizationController(
      AbstractOrganizationService organizationService,
      AbstractUserService userService) {
    this.organizationService = organizationService;
    this.userService = userService;
  }

  @GetMapping(value = "/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Organization get(@PathVariable UUID organizationId) {
    return organizationService
        .getOrganization(organizationId)
        .orElseThrow();
  }

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
}
