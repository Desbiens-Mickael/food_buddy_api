package fr.olprog_b.food_buddy.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "establishment_addresses")
public class EstablishmentAddress {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private Integer streetNumber;

  @Column()
  private String streetName;

  @Column(length = 5)
  private String zipCode;

  @Column()
  private String city;

  @Column( precision = 10, scale = 6, nullable = false)
  private BigDecimal latitude;

  @Column( precision = 10, scale = 6, nullable = false)
  private BigDecimal longitude;

  @Column(updatable = false)
  @CreationTimestamp()
  private LocalDateTime createdAt;

  @Column(updatable = true)
  @UpdateTimestamp()
  private LocalDateTime updatedAt;

  @OneToOne()
  @JoinColumn(name = "establishment_id", nullable = false)
  private Establishment establishment;
}
