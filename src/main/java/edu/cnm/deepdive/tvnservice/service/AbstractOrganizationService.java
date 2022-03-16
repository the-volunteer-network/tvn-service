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
   * delete this instance of  {@link Organization} from the Database.
   * @param externalKey passed to delete this instance from the Database
   * @param owner passed to delete this instance from the Database
   */
  void deleteOrganization(UUID externalKey, User owner);

  /**
   *  Add an {@link Opportunity} to the Database.
   * @param externalKey passed to modify this instance in the Database
   * @param receivedOrganization passed to modify this instance in  the Database
   * @param owner passed to modify this instance in the Database
   * @return
   */
  Optional<Organization> modifyOrganization(UUID externalKey, Organization receivedOrganization,
      User owner);

  /**
   *  Retrieved the specified {@link Organization} from the Database
   * @param organizationId passed to retrieve this instance from to the Database
   * @param user
   * @return the specified {@link Organization}
   */
  Optional<Organization> getOrganization(UUID organizationId, User user);

  /**
   * Retrieves the specified name from the database
   * @param organizationId passed to retrieve the specified name from the Database
   * @return
   */
  Optional<String> getName(UUID organizationId);

  /**
   *
   * @param organizationId
   * @param name
   * @param owner
   * @return
   */
  Optional<String> modifyName(UUID organizationId, String name, User owner);

  /**
   * @param user
   * @return
   */
  Iterable<Organization> getAll(User user);

  /**
   * @param user
   * @param fragment
   * @return
   */
  Iterable<Organization> searchByName(String fragment, User user);


}
