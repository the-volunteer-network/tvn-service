package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.UUID;

public class OpportunityService implements AbstractOpportunityService{

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
