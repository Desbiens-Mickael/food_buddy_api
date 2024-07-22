package fr.olprog_b.food_buddy.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity()
@Table(name = "reservations")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String validationCode;

  @Column(name = "valid_until", nullable = false)
  private LocalDateTime validUntil;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp()
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    this.validUntil = LocalDateTime.now().plusDays(1);
    this.validationCode = generateUniqueCode();
  }

  private String generateUniqueCode() {
      return UUID
        .randomUUID()
        .toString()
        .replaceAll("-", "")
        .substring(0, 8);
  }

  @ManyToOne()
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne()
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
}
