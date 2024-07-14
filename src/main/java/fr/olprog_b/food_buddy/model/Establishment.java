package fr.olprog_b.food_buddy.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "establishments")
public class Establishment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;

  @Column(unique = true, length = 14)
  private String siret;

  @Column()
  private String email;

  @Column()
  private String phoneNumber;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Column(updatable = true)
  private LocalDateTime updatedAt;

  @ManyToOne()
  @JoinColumn(name = "business_id", nullable = false)
  private Business business;

  @OneToOne(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
  private EstablishmentAddress address;
  
  @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products;
}
