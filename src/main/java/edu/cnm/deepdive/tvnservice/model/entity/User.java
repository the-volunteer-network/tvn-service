package edu.cnm.deepdive.tvnservice.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "organization_volunteer",
      joinColumns = @JoinColumn(name = "volunteer_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "organization_id", nullable = false, updatable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "volunteer_id"})
  )
  @OrderBy("name ASC")
  @JsonIgnore
  private final List<Organization> organizations = new LinkedList<>();

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "user_favorite",
      joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "organization_id", nullable = false, updatable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "user_id"})
  )
  @OrderBy("name ASC")
  @JsonIgnore
  private final List<Organization> favorites = new LinkedList<>();

  @NonNull
  public UUID getId() {
    return id;
  }

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

  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  /**
   * @return user's name.
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

  @NonNull
  public String getLocation() {
    return location;
  }

  public void setLocation(@NonNull String location) {
    this.location = location;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public List<Organization> getOrganizations() {
    return organizations;
  }

  public List<Organization> getFavorites() {
    return favorites;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, externalKey, created, name, displayName, location, email, phoneNumber);
  }

  @Override
  public boolean equals(Object obj) {
    boolean eq;
    if (this == obj) {
      eq = true;
    } else if (obj instanceof User) {
      User other = (User) obj;
      //noinspection ConstantConditions
      if (id != null && id.equals(other.id)) {
        eq = name.equals(other.name)
            && displayName.equals(other.displayName)
            && Objects.equals(location, other.location)
            && Objects.equals(email, other.email)
            && Objects.equals(phoneNumber, other.phoneNumber);
      } else {
        eq = false;
      }
    } else {
      eq = false;
    }
    return eq;
  }

  @PrePersist
  private void setAdditionalFields() {

    externalKey = UUID.randomUUID();

  }
}

