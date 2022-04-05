package edu.cnm.deepdive.tvnservice.model.dao;

import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides the core CRUD operations for the {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieves a {@link User} with the specified externalKey if it exists.
     * @param externalKey a unique identifier {@link User} resource.
     * @return the specified {@link User}
     */
    Optional<User> findByExternalKey(UUID externalKey);

    /**
     * Finds the instance of the {@link User} tied to the specified OauthKey.
     * @param oauthKey a unique identifier {@link User} resource.
     * @return the specified {@link User}
     */
    Optional<User> findByOauthKey(String oauthKey);

}
