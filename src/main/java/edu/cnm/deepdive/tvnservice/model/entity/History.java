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
  @Column(name = "history_id", updatable = false, nullable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID history_id;

  @NonNull
  @Column(nullable = true, updatable = true)
  private String user_favorite;

  @NonNull
  private String message;

  @NonNull
  private String search;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnore
  private User user;

}
