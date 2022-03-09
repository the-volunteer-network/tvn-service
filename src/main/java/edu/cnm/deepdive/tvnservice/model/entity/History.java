package edu.cnm.deepdive.tvnservice.model.entity;


import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.lang.NonNull;

@Entity
public class History {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "histories_id", updatable = false, nullable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;

  @NonNull
  private String userFavorite;

  @NonNull
  private String message;

  @NonNull
  private String search;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnore
  private User user;

  @NonNull
  public UUID getHistories_id() {
    return id;
  }

  @NonNull
  public String getUserFavorite() {
    return userFavorite;
  }

  public void setUserFavorite(@NonNull String userFavorite) {
    this.userFavorite = userFavorite;
  }

  @NonNull
  public String getMessage() {
    return message;
  }

  public void setMessage(@NonNull String message) {
    this.message = message;
  }

  @NonNull
  public String getSearch() {
    return search;
  }

  public void setSearch(@NonNull String search) {
    this.search = search;
  }

  @NonNull
  public User getUser() {
    return user;
  }

  public void setUser(@NonNull User user) {
    this.user = user;
  }

}
