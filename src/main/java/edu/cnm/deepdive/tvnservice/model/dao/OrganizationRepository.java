package edu.cnm.deepdive.tvnservice.model.dao;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides the core CRUD operations for the {@link Organization} entity.
 */
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  /**
   * Retrieves a {@link Organization} with the specified externalKey if it exists.
   * @param uuid passed on to retrieve the specified {@link Organization}
   * @return the specified {@link Organization}
   */
  Optional<Organization> findByExternalKey(UUID uuid);

  /**
   * Retrieves a {@link Organization} with the specified externalKey if it exists.
   * @param externalKey passed on to retrieve the specified owner
   * @param owner passed on to retrieve the specified owner
   * @return the specified {@link Organization}
   */
  Optional<Organization> findByExternalKeyAndOwner(UUID externalKey, User owner);

  /**
   * Searches and retrieves the {@link Organization} in the Database containing the passed parameter.
   * @param fragment String passed on in the search to retrieve a specified {@link Organization}
   * @return the specified {@link Organization}
   */
  Iterable<Organization> findByNameContainingOrderByNameAsc(String fragment);

  /**
   * Retrieves all of the {@link Organization} in the Database.
   * @return All of the {@link Organization} in the Database in  ascending order.
   */
  Iterable<Organization> getAllByOrderByNameAsc();
}
