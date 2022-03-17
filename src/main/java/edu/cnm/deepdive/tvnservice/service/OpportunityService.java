package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.OpportunityRepository;
import edu.cnm.deepdive.tvnservice.model.dao.OrganizationRepository;
import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.UUID;


 /**
 * Provides business functionalities for the {@link Opportunity} entity to interact with the application.
 */

public class OpportunityService implements AbstractOpportunityService{

  private final OpportunityRepository opportunityRepository;
  private final OrganizationRepository organizationRepository;

  /**
   *
   * @param repository
   * @param organizationRepository
   */
  public OpportunityService(OpportunityRepository repository,
      OrganizationRepository organizationRepository) {
    this.opportunityRepository = repository;
    this.organizationRepository = organizationRepository;
  }

  @Override
  public Opportunity addOpportunity(Opportunity opportunity, UUID externalOrganizationKey,
      User owner) {
    return null;
  }

  @Override
  public void deleteOpportunity(UUID externalKey, User owner) {

  }

  @Override
  public Opportunity modifyOpportunity(UUID externalKey, String name, String title,
      String neededSkill, String description, int availablePosition) {
    return null;
  }


}
