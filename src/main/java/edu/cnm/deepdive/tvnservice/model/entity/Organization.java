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
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;


/**
 * Encapsulates the property of the {@link Opportunity} of this service.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@JsonInclude(Include.NON_NULL)
public class Organization {


  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "organization_id", updatable = false, nullable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;

  @NonNull
  @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey;

  @NonNull
  @Column(nullable = false, unique = true)
  @NotNull
  @NotBlank
  private String name;

  @NonNull
  @Column(nullable = false)
  @NotNull
  @NotBlank
  private String about;

  @NonNull
  @Column(nullable = false)
  @NotNull
  @NotBlank
  private String mission;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date created;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(name = "user_favorite",
      joinColumns = @JoinColumn(name = "organization_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "user_id"})
  )
  @OrderBy("name ASC")
  @JsonIgnore
  private final List<User> volunteers = new LinkedList<>();


  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favorites")
  @OrderBy("name ASC")
  @JsonIgnore
  private final List<User> favoritingUsers = new LinkedList<>();

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
   *
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
   * @return
   */
  @NonNull
  public String getAbout() {
    return about;
  }

  /**
   * Sets the name of this user specific to {@code name}
   */
  public void setAbout(@NonNull String about) {
    this.about = about;
  }

  /**
   *
   * @return
   */
  @NonNull
  public String getMission() {
    return mission;
  }

  /**
   * Sets the name of this user specific to {@code name}
   */
  public void setMission(@NonNull String mission) {
    this.mission = mission;
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
   * @return
   */
  public User getOwner() {
    return owner;
  }

  /**
   * Sets the location of the specified {@link User specific to {@code location}}
   * @param owner
   */
  public void setOwner(User owner) {
    this.owner = owner;
  }

  /**
   *
   * @return
   */
  public List<User> getVolunteers() {
    return volunteers;
  }

  /**
   *
   * @return
   */
  public List<User> getFavoritingUsers() {
    return favoritingUsers;
  }

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
      eq = (obj instanceof Organization
          && getId() != null
          && getId().equals(((Organization) obj).getId()));
    }
    return eq;
  }

  @PrePersist
  private void setAdditionalFields() {
    externalKey = UUID.randomUUID();
  }
}
