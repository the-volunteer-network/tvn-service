package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.UUID;

/**
 * Method to be implemented in the {@link OpportunityService}
 */
public interface AbstractOpportunityService {

  // TODO Add a get method and make sure all methods pass within the user making the request
  // TODO Need to be able to get information by the external key

  /**
   *  Adds an {@link Opportunity} to the Database.
   * @param opportunity passed to be added to the Database
   * @param externalOrganizationKey passed to be added to the Database
   * @param owner passed to be added to the Database
   * @return
   */
  Opportunity addOpportunity(Opportunity opportunity, UUID externalOrganizationKey, User owner);

  /**
   * Deletes this instance of {@link Opportunity} from the database.
   * @param externalKey passed to be added to the database
   * @param owner passed to be added to the database
   */
  void deleteOpportunity(UUID externalKey, User owner);

  /**
   *  Modify the specified {@link Opportunity}
   * @param externalKey passed to modify this instance in the Database
   * @param name passed to modify this instance in the Database
   * @param title passed to modify this instance in the Database
   * @param neededSkill passed to modify this instance in the Database
   * @param description passed to modify this instance in the Database
   * @param availablePosition passed to modify this instance in the Databasese
   * @return this modified specified {@link Opportunity}
   */
  Opportunity modifyOpportunity(UUID externalKey, String name, String title, String neededSkill, String description, int availablePosition);

}
