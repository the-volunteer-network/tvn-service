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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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

  @Column(nullable = false)
  private double latitude;

  @Column(nullable = false)
  private double longitude;

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
  @JoinTable(name = "organization_volunteer",
      joinColumns = @JoinColumn(name = "organization_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
      uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "user_id"})
  )
  @OrderBy("name ASC")
  @JsonIgnore
  private final Set<User> volunteers = new LinkedHashSet<>();


  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favorites")
  @OrderBy("name ASC")
  @JsonIgnore
  private final Set<User> favoritingUsers = new LinkedHashSet<>();

  @NonNull
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("name ASC")
  @JsonProperty(access = Access.WRITE_ONLY)
  private final List<Opportunity> opportunities = new LinkedList<>();

  @Transient
  private boolean favorite;

  @Transient
  private boolean volunteer;

  @Transient
  private boolean owned;

  /**
   * Retrieves the id of the specified {@link Organization}.
   * @return
   */
  @NonNull
  public UUID getId() {
    return id;
  }

  /**
   * Retrieves the ExternalKey of the specified {@link Organization}.
   * @return
   */
  @NonNull
  public UUID getExternalKey() {
    return externalKey;
  }
  /**
   * Retrieves the name of the specified {@link Organization}.
   * @return
   */
  @NonNull
  public String getName() {
    return name;
  }
  /**
   * Sets the name of {@link Organization} specific to {@code name}
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Retrieves the {@code about} of the specified {@link Organization}.
   * @return
   */
  @NonNull
  public String getAbout() {
    return about;
  }

  /**
   * Sets the {@code about} of this {@link Organization}.
   */
  public void setAbout(@NonNull String about) {
    this.about = about;
  }

  /**
   * Retrieves the {@code mission} of the specified {@link Organization}.
   * @return
   */
  @NonNull
  public String getMission() {
    return mission;
  }

  /**
   * Sets the {@code mission} of this {@link Organization}.
   */
  public void setMission(@NonNull String mission) {
    this.mission = mission;
  }

  /**
   * Retrieves the {@code latitude} of this {@link Organization}
   * @return
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Sets the {@code latitude} of this {@link Organization}
   * @param latitude
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Retrieves the {@code longitude} of this {@link Organization}
   * @return
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Sets the {@code longitude} of this {@link Organization}
   * @param longitude
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * Retrieves the date of creation of this instance of {@link Organization}.
   * @return
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

  /**
   * Retrieves the {@code owner} the specified {@link Organization}
   * @return
   */
  public User getOwner() {
    return owner;
  }

  /**
   * Sets the {@code owner} of the specified {@link Organization}
   * @param owner
   */
  public void setOwner(User owner) {
    this.owner = owner;
  }

  /**
   * Retrieves the specified {@code volunteers}  of the specified {@link Organization}
   * @return
   */
  public Set<User> getVolunteers() {
    return volunteers;
  }

  /**
   * Sets the specified {@code favorite} relative to the specific {@link Organization} &amp; {@link User}.
   * @return
   */
  public Set<User> getFavoritingUsers() {
    return favoritingUsers;
  }

  /**
   * Retrieve a list of {@link Opportunity} tied to this {@link Organization}.
   * @return
   */
  @NonNull
  public List<Opportunity> getOpportunities() {
    return opportunities;
  }

  /**
   * Performs a check to see if this specific instance of {@link Organization}is a {@code favorite}.
   * @return
   */
  public boolean isFavorite() {
    return favorite;
  }

  /**
   * Sets this instance as a {@code favorite}
   * @param favorite
   */
  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  /**
   * Performs a check to see if this specific instance is a {@code volunteer}
   * @return
   */
  public boolean isVolunteer() {
    return volunteer;
  }

  /**
   * Sets this current instance as a {@code volunteer}
   * @param volunteer
   */
  public void setVolunteer(boolean volunteer) {
    this.volunteer = volunteer;
  }

  /**
   * Performs a check to see if this specific instance is a {@code volunteer}
   * @return
   */
  public boolean isOwned() {
    return owned;
  }

  /**
   * Sets this instance as owned {@link Organization}
   * @param owned
   */
  public void setOwned(boolean owned) {
    this.owned = owned;
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
