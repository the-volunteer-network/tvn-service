package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;

/**
 * Method to be implemented in the {@link OpportunityService}
 */
public interface AbstractOpportunityService {

  // TODO Add a get method and make sure all methods pass within the user making the request
  // TODO Need to be able to get information by the external key

  /**
   * Adds an {@link Opportunity} to the Database.
   *
   * @param opportunity
   * @param externalOrganizationKey
   * @param owner
   * @return
   */
  Opportunity addOpportunity(Opportunity opportunity, UUID externalOrganizationKey, User owner);

  /**
   * Deletes this instance of {@link Opportunity} from the database.
   *
   * @param externalKey
   * @param owner
   */
  void deleteOpportunity(UUID organizationExternalKey, UUID opportunityExternalKey, User owner);

  /**
   * Modify the specified instance of {@link Opportunity} in the database.
   *
   * @param externalKey
   * @return the modified instance of the specified {@link Opportunity}
   */
  Optional<Opportunity> modifyOpportunity(UUID organizationExternalKey, UUID opportunityExternalKey,
      Opportunity opportunity, User owner);

  Optional<Opportunity> getOpportunity(UUID organizationExternalKey, UUID opportunityExternalKey,
      User user);

  Iterable<Opportunity> getAllOpportunities(UUID organizationExternalKey);
}
