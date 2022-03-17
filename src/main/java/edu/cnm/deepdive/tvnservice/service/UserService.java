package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.OrganizationRepository;
import edu.cnm.deepdive.tvnservice.model.dao.UserRepository;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Provides business functionalities for the {@link User} entity to interact with the application.
 */
@Service
public class UserService implements AbstractUserService {

  private final UserRepository userRepository;
  private final OrganizationRepository organizationRepository;


  @Autowired
  public UserService(UserRepository userRepository,
      OrganizationRepository organizationRepository) {
    this.userRepository = userRepository;
    this.organizationRepository = organizationRepository;
  }

  @Override
  public User getOrCreate(String oauthKey, String displayName, String email, String givenName,
      String familyName) {
    return userRepository
        .findByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setName(String.format("%s, %s", familyName, givenName));
          user.setEmail(email);
          user.setOauthKey(oauthKey);
          user.setDisplayName(displayName);
          return userRepository.save(user);
        });

  }

  @Override
  public User getCurrentUser() {
    return (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
  }

  @Override
  public User updateUser(User received) {
    return null;
  }


  @Override
  public Optional<Boolean> setFavorite(UUID organizationExternalKey, User user, boolean favorite) {
    return organizationRepository
        .findByExternalKey(organizationExternalKey)
        .flatMap((organization) -> userRepository
            .findById(user.getId())
            .map((u) -> {
              if (favorite) {
                u.getFavorites().add(organization);
              } else {
                u.getFavorites().remove(organization);
              }
              userRepository.save(u);
              return favorite;
            }));
  }

  @Override
  public Optional<Boolean> getFavorite(UUID externalKey, User user) {
    return organizationRepository
        .findByExternalKey(externalKey)
        .map((org) -> user.getFavorites().contains(org));
  }

  @Override
  public Optional<Boolean> setVolunteer(UUID organizationExternalKey, User user,
      boolean volunteer) {
    return organizationRepository
        .findByExternalKey(organizationExternalKey)
        .map((org) -> {
          if (volunteer) {
            org.getVolunteers().add(user);
          } else {
            org.getVolunteers().remove(user);
          }
          organizationRepository.save(org);
          return volunteer;
        });
  }

  @Override
  public Optional<Boolean> getVolunteer(UUID organizationExternalKey, User user) {
    return organizationRepository
        .findByExternalKey(organizationExternalKey)
        .map((org)-> org.getVolunteers().contains(user));
  }
}
