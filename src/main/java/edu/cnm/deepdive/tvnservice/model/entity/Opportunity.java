package edu.cnm.deepdive.tvnservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;


/**
 * Encapsulates the property of the {@link Opportunity} of this service.
 */
@Entity
public class Opportunity {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "opportunity_id", updatable = false, nullable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;

  @NonNull
  @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey;

  @NonNull
  @Column(nullable = false, unique = true)
  private String name;

  @NonNull
  private String title;



  @NonNull
  private String neededSkill;

  @NonNull
  private String description;

  @NonNull
  @CreationTimestamp
  @Temporal(value= TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date calendar;

  @NonNull
  private int availablePosition;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "organization_id", nullable = false, updatable = false)
  @JsonIgnore
  private Organization organization;
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
  public String getTitle() {
    return title;
  }

  /**
   * Sets the location of the specified {@link User specific to {@code location}}
   * @param title
   */
  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  /**
   *
   * @return
   */
  @NonNull
  public String getNeededSkill() {
    return neededSkill;
  }

  /**
   * Sets the location of the specified {@link User specific to {@code location}}
   * @param neededSkill
   */
  public void setNeededSkill(@NonNull String neededSkill) {
    this.neededSkill = neededSkill;
  }

  /**
   *
   * @return
   */
  @NonNull
  public String getDescription() {
    return description;
  }

  /**
   * Sets the location of the specified {@link User specific to {@code location}}
   * @param description
   */
  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  /**
   *
   * @return
   */
  @NonNull
  public Date getCalendar() {
    return calendar;
  }

  /**
   *
   * @return
   */
  public int getAvailablePosition() {
    return availablePosition;
  }

  /**
   * Sets the location of the specified {@link User specific to {@code location}}
   * @param availablePosition
   */
  public void setAvailablePosition(int availablePosition) {
    this.availablePosition = availablePosition;
  }

  /**
   *
   * @return
   */
  @NonNull
  public Organization getOrganization() {
    return organization;
  }

  /**
   * Sets the location of the specified {@link User specific to {@code location}}
   * @param organization
   */
  public void setOrganization(@NonNull Organization organization) {
    this.organization = organization;
  }
}
