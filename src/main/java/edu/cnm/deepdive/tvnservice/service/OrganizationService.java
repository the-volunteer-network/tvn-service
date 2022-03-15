package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.OrganizationRepository;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService implements AbstractOrganizationService{

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

  }

  @Override
  public Organization modifyOrganization(Organization organization, User owner) {
    return null;
  }

  @Override
  public Optional<Organization> getOrganization(UUID externalKey) {
    return repository.findByExternalKey(externalKey);
  }

}
