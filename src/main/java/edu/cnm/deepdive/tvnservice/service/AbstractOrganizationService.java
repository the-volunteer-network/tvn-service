package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 * Method to be implemented in the {@link OpportunityService}
 */
public interface AbstractOrganizationService {

  // TODO Modify based on entire object at a time, OR multiple modify methods for each.
  // TODO Need to be able to get information by the external key

  /**
   * Add an {@link Organization} to the Database.
   * @param organization passed to be added to the Database
   * @param owner passed to be added to the Database
   * @return
   */
  Organization addOrganization(Organization organization, User owner);

  /**
   * Deletes this instance of {@link Organization} from the Database.
   * @param externalKey
   * @param owner
   */
  void deleteOrganization(UUID externalKey, User owner);

  /**
   *  Adds the specified instance of {@link Opportunity} to the Database.
   * @param externalKey
   * @param receivedOrganization
   * @param owner
   * @return the mofified instance of the specified {@link Organization}
   */
  Optional<Organization> modifyOrganization(UUID externalKey, Organization receivedOrganization,
      User owner);

  /**
   *  Retrieves the specified {@link Organization} from the Database
   * @param organizationId passed to retrieve this instance from to the Database
   * @param user
   * @return the specified {@link Organization}
   */
  Optional<Organization> getOrganization(UUID organizationId, User user);

  /**
   * Retrieves the specified name from the database
   * @param organizationId
   * @return the specified name.
   */
  Optional<String> getName(UUID organizationId);

  /**
   * Modifies the specified name if the value is present
   * @param organizationId
   * @param name
   * @param owner
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


}
