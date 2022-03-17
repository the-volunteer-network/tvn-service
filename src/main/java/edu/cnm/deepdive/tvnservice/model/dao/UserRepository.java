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
     * @param externalKey passed on to retrieve the specified {@link User}
     * @return the specified {@link User}
     */
    Optional<User> findByExternalKey(UUID externalKey);

    /**
     * Finds the instance of the {@link User}with the specified OauthKey.
     * @param oauthKey passed on to retrieve the specified {@link User}
     * @return the specified {@link User}
     */
    Optional<User> findByOauthKey(String oauthKey);

}
