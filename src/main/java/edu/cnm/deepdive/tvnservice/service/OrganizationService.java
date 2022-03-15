package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.OrganizationRepository;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService implements AbstractOrganizationService {

  private final OrganizationRepository repository;

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
        });
  }

  @Override
  public Optional<Organization> getOrganization(UUID externalKey) {
    return repository.findByExternalKey(externalKey);
  }

  @Override
  public Optional<String> getName(UUID organizationId) {
    return getOrganization(organizationId)
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


}
