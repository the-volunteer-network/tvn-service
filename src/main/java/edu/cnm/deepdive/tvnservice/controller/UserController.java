package edu.cnm.deepdive.tvnservice.controller;

import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.cnm.deepdive.tvnservice.service.UserService;

/**
 * Controller for the {@link User} entity class.
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final AbstractUserService userService;

  /**
   * Initialize this instance with {@link UserService},instance used to perform the requested operations.
   * @param userService provides access to high-level query &amp; persistence operations on {@link User} instances.
   */
  public UserController(AbstractUserService userService) {
    this.userService = userService;
  }

  /**
   * Return current user.
   */
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getProfile() {
    return userService.getCurrentUser();
  }

  /**
   *
   * @param organizationId
   * @return
   */
  @GetMapping(value = "/me/favorites/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean isFavorite(@PathVariable UUID organizationId) {
    return userService
        .getFavorite(organizationId, userService.getCurrentUser())
        .orElseThrow();
  }

  @PutMapping(value = "/me/favorites/{organizationId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean setFavorite(@PathVariable UUID organizationId, @RequestBody boolean favorite) {
    return userService
        .setFavorite(organizationId, userService.getCurrentUser(), favorite)
        .orElseThrow();
  }

  @GetMapping(value = "/me/favorites",produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Organization> getFavorites (){
    return userService
        .getCurrentUser()
        .getFavorites()
        .stream()
        .peek((org) -> org.setFavorite(true))
        .collect(Collectors.toList());
  }

  // TODO add/remove current user to volunteer for organization
  @GetMapping(value = "/me/volunteers/{organizationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean isVolunteer(@PathVariable UUID organizationId) {
    return userService
        .getVolunteer(organizationId, userService.getCurrentUser())
        .orElseThrow();
  }
  // todo mark a volunteer for an organization (cf: 3 above methods)
  @PutMapping(value = "/me/volunteers/{organizationId}")
  public boolean setVolunteer(@PathVariable UUID organizationId, @RequestBody boolean volunteer) {
    return userService

        .setVolunteer(organizationId, userService.getCurrentUser(), volunteer)
        .orElseThrow();
  }

  // Todo gets a list of organizations I am a  volunteer for
  @GetMapping(value = "/me/volunteers")
  public Iterable<Organization> getVolunteers () {
    return (userService
        .getCurrentUser()
        .getOrganizations()
        .stream()
        .peek((org) -> org.setVolunteer(true))
        .collect(Collectors.toList()));
  }


}
