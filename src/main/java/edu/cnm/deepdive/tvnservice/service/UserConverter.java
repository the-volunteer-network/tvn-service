package edu.cnm.deepdive.tvnservice.service;

import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Performs Object-to-String and String-to-Object conversions
 * between model data objects and a String representation of
 * those objects that is suitable for rendering.
 *
 */
@Service
public class UserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final AbstractUserService service;

  /**
   * Constructor for the {@link AbstractUserService} class
   * initializes and create two objects from included parameter.
   * @param service parameterized argument passed to this constructor
   */
  public UserConverter(AbstractUserService service) {
    this.service = service;
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt source) {
    Collection<SimpleGrantedAuthority> grants =
        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    User user = service.getOrCreate(source.getSubject(), source.getClaimAsString("name"),
        source.getClaimAsString("email"), source.getClaimAsString("given_name"),
        source.getClaimAsString("family_name"));
    return new UsernamePasswordAuthenticationToken(user, source.getTokenValue(), grants);
  }
}

