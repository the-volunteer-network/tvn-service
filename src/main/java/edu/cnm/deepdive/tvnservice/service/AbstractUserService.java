package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.User;

public interface AbstractUserService {


  User getOrCreate(String oauthKey, String displayName, String email, String givenName,
      String familyName);

  User getCurrentUser();

  User updateUser(User received);

}
