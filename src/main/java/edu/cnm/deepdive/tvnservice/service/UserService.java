package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.UserRepository;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public  class UserService implements AbstractUserService {

  private final UserRepository repository;


  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User getOrCreate(String oauthKey, String displayName, String email, String givenName,
      String familyName)  {
    return repository
        .findByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setName(String.format("%s, %s", familyName, givenName));
          user.setEmail(email);
          user.setOauthKey(oauthKey);
          user.setDisplayName(displayName);
          return repository.save(user);
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


}
