package edu.cnm.deepdive.tvnservice.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * Encapsulates the property of the user of this service.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    name = "user_profile"
)
@JsonInclude(Include.NON_NULL)
public class User {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "user_profile_id", updatable = false, nullable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;

  @NonNull
  @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey;

  @NonNull
  @Column(updatable = false, nullable = false, unique = true, length = 30)
  @JsonIgnore
  private String oauthKey;

  @NonNull
  @Column(nullable = false, unique = true, length = 50)
  private String displayName;

  @NonNull
  @Column(nullable = false, unique = true)
  private String name;

  private String location;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date created;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String phoneNumber;

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
  @OrderBy("name ASC")
  @JsonIgnore
  private final Set<Organization> ownedOrganizations = new LinkedHashSet<>();

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "volunteers")
  @OrderBy("name ASC")
  @JsonIgnore
  private final Set<Organization> organizations = new LinkedHashSet<>();

  @ManyToMany(fetch = FetchType.EAGER,
      cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "user_favorite",
      joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "organization_id", nullable = false, updatable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "user_id"})
  )
  @OrderBy("name ASC")
  @JsonIgnore
  private final Set<Organization> favorites = new LinkedHashSet<>();

  /**
   *
   * @return the id of the specified {@link User}
   */
  @NonNull
  public UUID getId() {
    return id;
  }

  /**
   * @return @return the ExternalKey of the specified {@link User}
   */
  @NonNull
  public UUID getExternalKey() {
    return externalKey;
  }

  /**
   * @return the Oauth Key of this user
   */
  @NonNull
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Sets the oAuth key of this user.
   *
   * @param oauthKey
   */
  public void setOauthKey(@NonNull String oauthKey) {
    this.oauthKey = oauthKey;
  }

  /**
   *
   * @return the display name of the specified {@link User}
   */
  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Sets the display name of the specified {@link User} specific to {@code displayName}
   * @param displayName
   */
  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  /**
   * @return the name of the specified {@link User}.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this user specific to {@code name}
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }


  /**
   *
   * @return the location of a specified {@link User}.
   */
  @NonNull
  public String getLocation() {
    return location;
  }

  /**
   * Sets the location of the specified {@link User specific to {@code location}}
   * @param location
   */
  public void setLocation(@NonNull String location) {
    this.location = location;
  }

  /**
   *
   * @return the date of creation of this specified {@link User}
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

  /**
   *
   * @return the email of the specified {@link User}
   */
  @NonNull
  public String getEmail() {
    return email;
  }

  /**
   * Sets the name of this user specific to {@code name}
   * @param email
   */
  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  /**
   *
   @return the email of the specified {@link User}   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the name of this user specific to {@code name}
   * @param phoneNumber
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   *
   * @return
   */
  @NonNull
  public Set<Organization> getOwnedOrganizations() {
    return ownedOrganizations;
  }

  /**
   *
   @return the email of the specified {@link User}
   */
  public Set<Organization> getOrganizations() {
    return organizations;
  }

  /**
   *
   @return the email of the specified {@link User}
   */
  public Set<Organization> getFavorites() {
    return favorites;
  }

  public Set<Organization>

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Override
  public boolean equals(Object obj) {
    boolean eq;
    if (this == obj) {
      eq = true;
    } else {
      //noinspection ConstantConditions
      eq = (obj instanceof User
          && getId() != null
          && getId().equals(((User) obj).getId()));
    }
    return eq;
  }

  @PrePersist
  private void setAdditionalFields() {

    externalKey = UUID.randomUUID();

  }
}

