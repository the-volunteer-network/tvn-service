package edu.cnm.deepdive.tvnservice.model.dao;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
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
   *
   * @param uuid a unique identifier {@link Opportunity} resource.
   * @return the specified {@link  Opportunity}
   */
  Optional<Opportunity> findByExternalKey(UUID uuid);

  /**
   * Retrieves the specified  {@link Opportunity}
   * @param externalKey a unique identifier {@link Opportunity} resource.
   * @param organization the specified {@link Organization} this {@link Opportunity} is tied to.
   * @return
   */
  Optional<Opportunity> findByExternalKeyAndOrganization(UUID externalKey,
      Organization organization);

  /**
   * Searches and retrieves an {@link Opportunity} in the Database containing the passed parameter.
   * @param fragment String passed on in the search to retrieve a specified {@link Opportunity}
   * @return the specified {@link Opportunity}
   */
  Iterable<Opportunity> findByNameContainingOrderByName(String fragment);

  /**
   * Searches and retrieves an {@link Opportunity} in the Database containing the passed parameter and the specified {@link Organization}
   * @param fragment String passed on in the search to retrieve a specified {@link Opportunity}
   * @param organization  specified {@link Organization passed to retrieve this instance.}
   * @return
   */
  Iterable<Opportunity> findByNameContainingAndOrganizationOrderByName(String fragment, Organization organization);
}
