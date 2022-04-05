package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 * High-level operations available to be performed on the {@link Opportunity}
 */
public interface AbstractOpportunityService {

  // TODO Add a get method and make sure all methods pass within the user making the request
  // TODO Need to be able to get information by the external key

  /**
   * Adds an {@link Opportunity} to the Database.
   *
   * @param opportunity instance to be added.
   * @param externalOrganizationKey a unique identifier {@link Opportunity} resource.
   * @param owner
   * @return the specified {@link Opportunity}
   */
  Opportunity addOpportunity(Opportunity opportunity, UUID externalOrganizationKey, User owner);

  /**
   * Deletes this instance of {@link Opportunity} from the database.
   *
   * @param opportunityExternalKey  a unique identifier {@link Opportunity} resource.
   * @param organizationExternalKey  a unique identifier {@link Organization} resource.
   * @param owner
   */
  void deleteOpportunity(UUID organizationExternalKey, UUID opportunityExternalKey, User owner);

  /**
   * Modifies the specified instance of {@link Opportunity} in the database.
   *
   * @param opportunityExternalKey  a unique identifier {@link Opportunity} resource.
   * @param organizationExternalKey a unique identifier {@link Organization} resource.
   * @param owner
   * @return the modified instance of the specified {@link Opportunity}
   */
  Optional<Opportunity> modifyOpportunity(UUID organizationExternalKey, UUID opportunityExternalKey,
      Opportunity opportunity, User owner);

  /**
   *  Retrieves the specified {@link Organization} from the Database
   * @param organizationExternalKey a unique identifier {@link Organization} resource.
   * @param opportunityExternalKey a unique identifier {@link Opportunity} resource.
   * @param user
   * @return the specified {@link Opportunity}.
   */
  Optional<Opportunity> getOpportunity(UUID organizationExternalKey, UUID opportunityExternalKey,
      User user);

  /**
   * Retrieves all the {@link Organization}
   * @param organizationExternalKey a unique identifier {@link Organization} resource.
   * @return all of the instances of {@link Opportunity}
   */
  Iterable<Opportunity> getAllOpportunities(UUID organizationExternalKey);
}
