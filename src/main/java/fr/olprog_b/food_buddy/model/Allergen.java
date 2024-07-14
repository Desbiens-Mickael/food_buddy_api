package fr.olprog_b.food_buddy.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity()
@Table(name = "allergens")
public class Allergen {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(updatable = false)
  @CreationTimestamp()
  private LocalDateTime createdAt;

  @Column(updatable = true)
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  // Relations

  @ManyToMany(mappedBy = "allergens")
  private List<Product> products;
}
