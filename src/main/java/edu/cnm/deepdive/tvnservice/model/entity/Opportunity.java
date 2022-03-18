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
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
  @NotNull
  @NotBlank
  private String name;

  @NonNull
  private String title;

  @NonNull
  private String neededSkill;

  @NonNull
  private String description;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date created;



  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "organization_id", nullable = false, updatable = false)
  @JsonIgnore
  private Organization organization;
  /**
   * Retrieves the id of the specified {@link Opportunity}
   * @return
   */
  @NonNull
  public UUID getId() {
    return id;
  }
  /**
   * Retrieves the {@code externalKey} of the specified {@link Opportunity}
   * @return
   */
  @NonNull
  public UUID getExternalKey() {
    return externalKey;
  }
  /**
   * Retrieves {@code name} of the specified {@link Opportunity}.
   * @return
   */
  @NonNull
  public String getName() {
    return name;
  }
  /**
   * Sets the {@code name} of this {@link Opportunity}
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Retrieves the specified {@code title}.
   * @return
   */
  @NonNull
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the specified {@link Opportunity}.
   * @param title
   */
  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  /**
   * Retrieves the specified {@code neededSkill}.
   * @return
   */
  @NonNull
  public String getNeededSkill() {
    return neededSkill;
  }

  /**
   * Sets the {@code needed skill} of the specified {@link Opportunity}
   * @param neededSkill
   */
  public void setNeededSkill(@NonNull String neededSkill) {
    this.neededSkill = neededSkill;
  }

  /**
   * Retrieves the specified {@code description} specific to {@link Opportunity}.
   * @return
   */
  @NonNull
  public String getDescription() {
    return description;
  }

  /**
   * Sets the specified {@code description} specific to {@link Opportunity}.
   * @param description
   */
  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  /**
   *  Returns the date of creation of this instance of {@link Opportunity}.
   * @return
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

  /**
   * Retrieves the specified {@link Organization} tied to this {@link Opportunity}.
   * @return
   */
  @NonNull
  public Organization getOrganization() {
    return organization;
  }

  /**
   * Sets the specified {@link Organization} tied to this {@link Opportunity}
   * @param organization
   */
  public void setOrganization(@NonNull Organization organization) {
    this.organization = organization;
  }

  @PrePersist
  private void setAdditionalFields() {
    externalKey = UUID.randomUUID();
  }
}
