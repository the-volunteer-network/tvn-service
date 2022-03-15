package edu.cnm.deepdive.tvnservice.controller;

import edu.cnm.deepdive.tvnservice.model.entity.User;
import edu.cnm.deepdive.tvnservice.service.AbstractUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.cnm.deepdive.tvnservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  private final AbstractUserService userService;

  /**
   * Initializes this instance with {@link UserService},instance used to perform the requested operations.
   * @param userService provides access to high-level query &amp; persistence operations on {@link User} instances.
   */
  public UserController(AbstractUserService userService) {
    this.userService = userService;
  }

  /**
   * Returns current user.
   *
   */
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getProfile() {
    return userService.getCurrentUser();
  }
}
