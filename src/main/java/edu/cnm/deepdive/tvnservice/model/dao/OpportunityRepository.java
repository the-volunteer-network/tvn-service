package edu.cnm.deepdive.tvnservice.model.dao;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides the core CRUD operations for the {@link Opportunity} entity.
 */
public interface OpportunityRepository extends JpaRepository<Opportunity, UUID> {

  /**
   * Retrieves a {@link Opportunity} with the specified externalKey if it exists.
   * @param uuid passed on to retrieve the specified {@link Opportunity}
   * @return the specified {@link  Opportunity}
   */
  Optional<Opportunity> findByExternalKey(UUID uuid);


}
