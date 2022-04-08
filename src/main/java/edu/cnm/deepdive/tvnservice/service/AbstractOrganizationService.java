package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 * High-level operations available to be performed on the {@link OrganizationService}
 */
public interface AbstractOrganizationService {

  // TODO Modify based on entire object at a time, OR multiple modify methods for each.
  // TODO Need to be able to get information by the external key

  /**
   * Adds an {@link Organization} to the Database.
   * @param organization instance to be added
   * @param owner tied to this {@link Organization}
   * @return the added {@link Organization}
   */
  Organization addOrganization(Organization organization, User owner);

  /**
   * Deletes this instance of {@link Organization} from the Database.
   * @param externalKey a unique identifier {@link Organization} resource.
   * @param owner owner tied to this instance
   */
  void deleteOrganization(UUID externalKey, User owner);

  /**
   * Modifies the specified instance of {@link Organization} to the Database.
   * @param externalKey a unique identifier {@link Organization} resource.
   * @param receivedOrganization
   * @param owner   tied to this instance of {@link Organization}
   * @return the modified instance of the specified {@link Organization}
   */
  Optional<Organization> modifyOrganization(UUID externalKey, Organization receivedOrganization,
      User owner);

  /**
   *  Retrieves the specified {@link Organization} from the Database.
   * @param organizationId passed to retrieve this instance from to the Database.
   * @param user tied to this instance.
   * @return the specified {@link Organization}.
   */
  Optional<Organization> getOrganization(UUID organizationId, User user);

  /**
   * Retrieves the specified name from the database.
   * @param organizationId a unique identifier {@link Organization} resource.
   * @return the specified name.
   */
  Optional<String> getName(UUID organizationId);

  /**
   * Modifies the specified name if the value is present.
   * @param organizationId a unique identifier {@link Organization} resource.
   * @param name tied to this instance
   * @param owner tied to this instance
   * @return the modified name;
   */
  Optional<String> modifyName(UUID organizationId, String name, User owner);

  /**
   * Retrieves all the {@link Organization}
   * @param user
   * @return a List of all the specified {@link Organization}
   */
  Iterable<Organization> getAll(User user);

  /**
   * Searches and Retrieves the specified {@link Organization}
   * @param user
   * @param fragment
   * @return the specified {@link Organization}
   */
  Iterable<Organization> searchByName(String fragment, User user);


  Iterable<Organization> getOwned(User user);
}
