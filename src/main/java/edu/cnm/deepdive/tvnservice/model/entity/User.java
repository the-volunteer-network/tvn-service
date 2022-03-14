package edu.cnm.deepdive.tvnservice.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    name = "user_profile"
)
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
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
      cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<History> histories = new LinkedList<>();

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "organization_volunteer",
      joinColumns = @JoinColumn(name = "volunteer_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "organization_id", nullable = false, updatable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "volunteer_id"})
  )
  @OrderBy("name ASC")
  private final List<Organization> organizations = new LinkedList<>();

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "user_favorite",
      joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "organization_id", nullable = false, updatable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "user_id"})
  )
  @OrderBy("name ASC")
  private final List<Organization> favorites = new LinkedList<>();

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public UUID getExternalKey() {
    return externalKey;
  }

  @NonNull
  public String getOauthKey() {
    return oauthKey;
  }

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

  @NonNull
  public String getName() {
    return name;
  }

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

  @NonNull
  public List<History> getHistories() {
    return histories;
  }

  public List<Organization> getOrganizations() {
    return organizations;
  }

  public List<Organization> getFavorites() {
    return favorites;
  }
}

