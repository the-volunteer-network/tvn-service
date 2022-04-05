package edu.cnm.deepdive.tvnservice.model.dao;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides the core CRUD operations for the {@link Organization} entity.
 */
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  /**
   * Retrieves an {@link Organization} tied to this specified externalKey if it exists.
   * @param uuid a unique identifier {@link Organization} resource.
   * @return the specified {@link Organization}
   */
  Optional<Organization> findByExternalKey(UUID uuid);

  /**
   * Retrieves a {@link Organization} with the specified externalKey if it exists.
   * @param externalKey a unique identifier {@link Organization} resource.
   * @param owner the instance of owner tied to that {@link Organization}
   * @return the specified {@link Organization}
   */
  Optional<Organization> findByExternalKeyAndOwner(UUID externalKey, User owner);

  /**
   * Searches and retrieves an {@link Organization} in the Database containing the passed parameter.
   * @param fragment String passed on in the search to retrieve a specified {@link Organization}
   * @return the specified {@link Organization}
   */
  List<Organization> findByNameContainingOrderByNameAsc(String fragment);

  /**
   * Retrieves all of the {@link Organization} in the Database.
   * @return All of the {@link Organization} in the Database in  ascending order.
   */
  List<Organization> getAllByOrderByNameAsc();
}
