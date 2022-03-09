package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.dao.UserRepository;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class UserService implements AbstractUserService {

  private final UserRepository repository;

  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User getOrCreate(String oauthKey, String displayName)  {
    // TODO Bearer token required
    //  -> populate location/name
    //  -> this data needs to be passed as argument here
    return repository
        .findByOauthKey(oauthKey)
//        .map((user -> {
//          user.setConnected(new Date());
//          return repository.save(user);
//        }))
        // TODO Use setters from User class
        //  -> Set additional fields prior to save
        .orElseGet(() -> {
          User user = new User();
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


}
