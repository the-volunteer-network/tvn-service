package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;

public interface AbstractOrganizationService {

  // TODO Modify based on entire object at a time, OR multiple modify methods for each.
  // TODO Need to be able to get information by the external key

  Organization addOrganization(Organization organization, User owner);

  void deleteOrganization(UUID externalKey, User owner);

  Optional<Organization> modifyOrganization(UUID externalKey, Organization receivedOrganization,
      User owner);

  Optional<Organization> getOrganization(UUID organizationId);

  Optional<String> getName(UUID organizationId);

  Optional<String> modifyName(UUID organizationId, String name, User owner);

  Iterable<Organization> getAll();

  Iterable<Organization> searchByName(String fragment);


}
