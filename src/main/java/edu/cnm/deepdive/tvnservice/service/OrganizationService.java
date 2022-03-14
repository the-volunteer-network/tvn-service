package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.UUID;

public class OrganizationService implements AbstractOrganizationService{

  @Override
  public Organization addOrganization(Organization organization, User owner) {
    return null;
  }

  @Override
  public void deleteOrganization(UUID externalKey, User owner) {

  }

  @Override
  public Organization modifyOrganization(UUID externalKey, String name, String about,
      String mission, User owner) {
    return null;
  }
}
