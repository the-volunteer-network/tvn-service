package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.UUID;

public interface AbstractOrganizationService {

  Organization addOrganization(Organization organization, User owner);

  void deleteOrganization(UUID externalKey, User owner);

  Organization modifyOrganization(UUID externalKey, String name, String about, String mission,
      User owner);



}
