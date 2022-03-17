package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.OrganizationRepository;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


/**
 * Provides business functionalities for the {@link Organization} entity to interact with the application.
 */
@Service
public class OrganizationService implements AbstractOrganizationService {

  private final OrganizationRepository repository;
  private final BiFunction<Organization, User , Organization> favoriteMarker = (org, user) -> {
    if (user != null) {
      org.setFavorite(user.getFavorites().contains(org));
    }else {
      org.setFavorite(false);
    }
    return org;
  };


  public OrganizationService(
      OrganizationRepository repository) {
    this.repository = repository;
  }

  @Override
  public Organization addOrganization(Organization organization, User owner) {
    organization.setOwner(owner);
    return repository.save(organization);
  }

  @Override
  public void deleteOrganization(UUID externalKey, User owner) {
    repository
        .findByExternalKeyAndOwner(externalKey, owner)
        .ifPresent(repository::delete);
  }

  @Override
  public Optional<Organization> modifyOrganization(UUID externalKey,
      Organization receivedOrganization, User owner) {
    return repository
        .findByExternalKeyAndOwner(externalKey, owner)
        .map((organization) -> {
          organization.setName(receivedOrganization.getName());
          organization.setAbout(receivedOrganization.getAbout());
          organization.setMission(receivedOrganization.getMission());
          return repository.save(organization);
        })
        .map((org) -> favoriteMarker.apply(org, owner));
  }

  @Override
  public Optional<Organization> getOrganization(UUID externalKey,User user) {
    return repository
        .findByExternalKey(externalKey)
        .map((org) -> favoriteMarker.apply(org,user));
  }

  @Override
  public Optional<String> getName(UUID organizationId) {
    return getOrganization(organizationId, null)
        .map(Organization::getName);
  }

  @Override
  public Optional<String> modifyName(UUID organizationId, String name, User owner) {
    return repository
        .findByExternalKeyAndOwner(organizationId, owner)
        .map((organization) -> {
          organization.setName(name);
          return repository.save(organization);
        })
        .map(Organization::getName);
  }

  @Override
  public Iterable<Organization> getAll(User user) {
    return repository
        .getAllByOrderByNameAsc()
        .stream()
        .map((org) -> favoriteMarker.apply(org, user))
        .collect(Collectors.toList());
  }

  @Override
  public Iterable<Organization> searchByName(String fragment, User user) {
    return repository
        .findByNameContainingOrderByNameAsc(fragment)
        .stream()
        .map((org) -> favoriteMarker.apply(org, user))
        .collect(Collectors.toList());
  }


}
