package edu.cnm.deepdive.tvnservice.controller;

import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import edu.cnm.deepdive.tvnservice.service.AbstractOrganizationService;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import edu.cnm.deepdive.tvnservice.service.UserService;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles REST requests for operation on individual instances and collections of {@link  User}
 * entity.
 */
@RestController
@RequestMapping(PathComponents.USERS)
public class UserController {

  private final AbstractUserService userService;
  private final AbstractOrganizationService organizationService;

  /**
   * Initialize this instance with {@link UserService},instance used to perform the requested
   * operations.
   *
   * @param userService provides access to high-level query &amp; persistence operations on {@link
   *                    User} instances.
   * @param organizationService
   */
  public UserController(AbstractUserService userService,
      AbstractOrganizationService organizationService) {
    this.userService = userService;
    this.organizationService = organizationService;
  }

  /**
   * Retrieves the current {@link User} of the service
   * Returns the current user.
   */
  @GetMapping(value = PathComponents.CURRENT_USER, produces = MediaType.APPLICATION_JSON_VALUE)
  public User getProfile() {
    return userService.getCurrentUser();
  }

  /**
   * Modifies the profile of the {@link User}
   * @param user
   * @return
   */
  @PutMapping(value = PathComponents.CURRENT_USER, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public User modifyProfile(@RequestBody @Valid User user) {
    return userService.updateUser(user, userService.getCurrentUser());
  }

  /**
   * Retrieve an instance of favorite by the {@link Organization} id, if the instance of favorite is
   * true.
   * @param organizationId a unique identifier {@link Organization} resource.
   * @return an instance of favorite.
   */
  @GetMapping(value = PathComponents.FAVORITE_ORG, produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean isFavorite(@PathVariable UUID organizationId) {
    return userService
        .getFavorite(organizationId, userService.getCurrentUser())
        .orElseThrow();
  }

  /**
   * Find an organization by its id, if the instance of favorite  is true, the instance is added,
   * otherwise it is removed.
   *
   * @param organizationId a unique identifier {@link Organization} resource.
   * @param favorite       an instance of favorite
   * @return this instance of favorite
   */
  @PutMapping(value = PathComponents.FAVORITE_ORG, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean setFavorite(@PathVariable UUID organizationId, @RequestBody boolean favorite) {
    return userService
        .setFavorite(organizationId, userService.getCurrentUser(), favorite)
        .orElseThrow();
  }

  /**
   * Retrieves the specified instances of favorites the {@link User} is tied to.
   *
   * @return a list of all the favorites.
   */
  @GetMapping(value = PathComponents.ALL_FAVORITES, produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Organization> getFavorites() {
    return userService
        .getCurrentUser()
        .getFavorites()
        .stream()
        .peek((org) -> org.setFavorite(true))
        .collect(Collectors.toList());
  }

  // TODO add/remove current user to volunteer for organization

  /**
   * Returns the instances of volunteer the user is tied to.
   * @param organizationId
   * @return
   */
  @GetMapping(value = PathComponents.VOLUNTEER_ORG, produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean isVolunteer(@PathVariable UUID organizationId) {
    return userService
        .getVolunteer(organizationId, userService.getCurrentUser())
        .orElseThrow();
  }

  // todo mark a volunteer for an organization (cf: 3 above methods)

  /**
   * Find an organization by its id, if the instance of volunteer  is true, the instance is added to the database
   * otherwise it is removed.
   * @param organizationId a unique identifier {@link Organization} resource.
   * @param volunteer passed on to set this instance of volunteer
   * @return  the specified volunteer.
   */
    @PutMapping(value = PathComponents.VOLUNTEER_ORG, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean setVolunteer(@PathVariable UUID organizationId, @RequestBody boolean volunteer) {
      return userService
          .setVolunteer(organizationId, userService.getCurrentUser(), volunteer)
          .orElseThrow();
  }

  // Todo gets a list of organizations I am a  volunteer for

  /**
   *
   * @return all the instances of volunteers tied to this {@link User}
   */
  @GetMapping(value = PathComponents.ALL_VOLUNTEERS, produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Organization> getVolunteers() {
    return new ArrayList<>(userService.getCurrentUser().getOrganizations());
  }

  /**
   * Returns the owned {@link Organization} for the specified user.
   * @return
   */

  @GetMapping(value = PathComponents.ALL_OWNED_ORGANIZATIONS, produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Organization> getOwnedOrganizations() {
    return organizationService
        .getOwned(userService.getCurrentUser());
  }
}
