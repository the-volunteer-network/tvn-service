package edu.cnm.deepdive.tvnservice.model.dao;

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
     * @param externalKey
     * @return
     */
    Optional<User> findByExternalKey(UUID externalKey);

    Optional<User> findByOauthKey(String oauthKey);

}
