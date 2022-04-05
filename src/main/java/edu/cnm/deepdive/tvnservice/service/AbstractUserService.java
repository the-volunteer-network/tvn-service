package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;

/**
 * High-level operations available to be performed on the {@link UserService}
 */
public interface AbstractUserService {

  /**
   * Create an Instance of this {@link User} in the database
   * @param oauthKey a unique identifier {@link Organization} resource.
   * @param displayName
   * @param email
   * @param givenName
   * @param familyName
   * @return
   */
  User getOrCreate(String oauthKey, String displayName, String email, String givenName,
      String familyName);

  /**
   * Retrieves the specified {@link User}
   * @return the instance of this current {@link User}
   */
  User getCurrentUser();

  /**
   * Update the specified {@link User} in the Database
   * @param received passed to update this instance in the Database
   * @return the updated {@link User}
   */
  User updateUser(User received, User currentUser);

  /**
   * Set a favorite instance of {@link Organization }
   * @param externalKey a unique identifier {@link Organization} resource.
   * @param user  tied to this instance of favorite
   * @param favorite
   * @return  a boolean instance of this favorite
   */
  Optional<Boolean> setFavorite(UUID externalKey, User user, boolean favorite) ;

  /**
   * Retrieves a favorite instance of an {@link Organization} from the database/
   * @param externalKey a unique identifier {@link Organization} resource.
   * @param user  passed on to retrieve this instance
   * @return  an Optional favorite from the database
   */
  Optional<Boolean> getFavorite(UUID externalKey, User user);

  /**
   * Set the specified Volunteer to the database,if this instance is not already set in the database.
   * @param organizationExternalKey a unique identifier {@link Organization} resource.
   * @param user passed on to set this instance
   * @param volunteer passed on to set this instance
   * @return the specified volunteer.
   */
  Optional<Boolean> setVolunteer(UUID organizationExternalKey, User user, boolean volunteer);

  /**
   * Retrieves all the volunteers  from the database if the boolean volunteer returns true.
   * @param organizationExternalKey a unique identifier {@link Organization} resource.
   * @param user
   * @return the specified volunteers
   */
  Optional<Boolean> getVolunteer(UUID organizationExternalKey,  User user);
}
