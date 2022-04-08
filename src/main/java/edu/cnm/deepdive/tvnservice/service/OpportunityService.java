package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.OpportunityRepository;
import edu.cnm.deepdive.tvnservice.model.dao.OrganizationRepository;
import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;


/**
 * Provides business functionalities for the {@link Opportunity} entity to interact with the application.
 */

 @Service
public class OpportunityService implements AbstractOpportunityService{

  private final OpportunityRepository opportunityRepository;
  private final OrganizationRepository organizationRepository;

  /**
   * Constructor for the {@link OpportunityService} class
   * initializes and creates objects from included parameters
   * @param repository
   * @param organizationRepository
   */
  public OpportunityService(OpportunityRepository repository,
      OrganizationRepository organizationRepository) {
    this.opportunityRepository = repository;
    this.organizationRepository = organizationRepository;
  }

  @Override
  public Opportunity addOpportunity(Opportunity opportunity, UUID organizationExternalKey,
      User owner) {
    return organizationRepository
        .findByExternalKeyAndOwner(organizationExternalKey, owner)
        .map((org) -> {
          opportunity.setOrganization(org);
          return opportunityRepository.save(opportunity);
        })
        .orElseThrow();
  }

  @Override
  public void deleteOpportunity(UUID organizationExternalKey,UUID opportunityExternalKey, User owner) {
    organizationRepository
        .findByExternalKeyAndOwner(organizationExternalKey, owner)
        .ifPresent((org) -> opportunityRepository
            .findByExternalKeyAndOrganization(opportunityExternalKey, org)
            .ifPresent((opp) -> {
              org.getOpportunities().remove(opp);
              organizationRepository.save(org);
            })
        );
  }

   @Override
   public Optional<Opportunity> modifyOpportunity(UUID organizationExternalKey,UUID opportunityExternalKey, Opportunity opportunity, User owner) {
     return organizationRepository
         .findByExternalKeyAndOwner(organizationExternalKey, owner)
         .flatMap((org) -> opportunityRepository.findByExternalKeyAndOrganization(opportunityExternalKey, org))
         .map((opp) -> {
           opp.setName(opportunity.getName());
           opp.setDescription(opportunity.getDescription());
           opp.setTitle(opportunity.getTitle());
           opp.setNeededSkill(opportunity.getNeededSkill());
           return opportunityRepository.save(opp);
         });
   }

   @Override
   public Optional<Opportunity> getOpportunity(UUID organizationExternalKey, UUID opportunityExternalKey, User user) {
     return organizationRepository
         .findByExternalKey(organizationExternalKey)
         .flatMap((org) -> opportunityRepository.findByExternalKeyAndOrganization(opportunityExternalKey, org));
   }

  @Override
  public Iterable<Opportunity> getAllOpportunities(UUID organizationExternalKey) {
    return organizationRepository
        .findByExternalKey(organizationExternalKey)
        .map(Organization::getOpportunities)
        .orElseThrow();
  }

  @Override
  public Iterable<Opportunity> searchByName(String fragment) {
    return opportunityRepository
        .findByNameContainingOrderByName(fragment);

  }

  @Override
  public Iterable<Opportunity> searchByNameAndOrganization(String fragment,
      UUID organizationExternalKey) {
    return organizationRepository
        .findByExternalKey(organizationExternalKey)
        .map((org) -> opportunityRepository.findByNameContainingAndOrganizationOrderByName(fragment, org))
        .orElseThrow();
  }

}
