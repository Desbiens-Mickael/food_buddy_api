package fr.olprog_b.food_buddy.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "businesses")
public class Business {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, length = 9)
  private String siren;

  @Column()
  private String name;

  @Column(nullable = true)
  private String logoUrl;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp()
  private LocalDateTime createdAt;

  @Column(nullable = false, updatable = true)
  @UpdateTimestamp()
  private LocalDateTime updatedAt;

  // Relations
  
  @ManyToOne()
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Establishment> establishments;
}
