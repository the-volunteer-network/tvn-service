package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.UUID;

public interface AbstractOpportunityService {

  // TODO Add, delete, modify

  Opportunity addOpportunity(Opportunity opportunity, UUID externalOrganizationKey, User owner);

  void deleteOpportunity(UUID externalKey, User owner);

  Opportunity modifyOpportunity(UUID externalKey, String name, String title, String neededSkill, String description, int availablePosition);

}
