package edu.cnm.deepdive.tvnservice.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
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
  @Column(nullable = false, updatable = false, unique = true)
  private String name;

  @NonNull
  @Column(nullable = false)
  private String about;

  @NonNull
  @Column(nullable = false)
  private String mission;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date created;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
  @OrderBy("name ASC")
  private final List<User> volunteers = new LinkedList<>();


  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favorites")
  @OrderBy("name ASC")
  private final List<User> favoritingUsers = new LinkedList<>();


  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public UUID getExternalKey() {
    return externalKey;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @NonNull
  public String getAbout() {
    return about;
  }

  public void setAbout(@NonNull String about) {
    this.about = about;
  }

  @NonNull
  public String getMission() {
    return mission;
  }

  public void setMission(@NonNull String mission) {
    this.mission = mission;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public List<User> getVolunteers() {
    return volunteers;
  }
  public List<User> getFavoritingUsers() {
    return favoritingUsers;
  }
}
