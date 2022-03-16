package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 * high-level operations available to be performed on
 * Method to be implemented in the {@link UserService}
 */
public interface AbstractUserService {

  /**
   * Create an Instance of this {@link User} in the database
   * @param oauthKey passed to create this instance in the Database
   * @param displayName passed to create this instance in  the Database
   * @param email passed to create this instance in the Database
   * @param givenName passed to create this instance in the Database
   * @param familyName passed to create this instance in the Database
   * @return
   */
  User getOrCreate(String oauthKey, String displayName, String email, String givenName,
      String familyName);

  /**
   *
   * @return the instance of this current {@link User}
   */
  User getCurrentUser();

  /**
   *  Update the {@link User} in the Database
   * @param received passed to update this instance in  the Database
   * @return the updated {@link User}
   */
  User updateUser(User received);

  /**
   *
   * @param externalKey
   * @param user
   * @param favorite
   * @return
   */
  Optional<Boolean> setFavorite(UUID externalKey, User user, boolean favorite) ;

  Optional<Boolean> getFavorite(UUID externalKey, User user);

  Optional<Boolean> setVolunteer(UUID organizationExternalKey, User user, boolean volunteer);

  Optional<Boolean> getVolunteer(UUID organizationExternalKey,  User user);
}
