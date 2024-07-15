package fr.olprog_b.food_buddy.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import fr.olprog_b.food_buddy.enums.ProductType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity()
@Table(name = "daily_consumptions")
public class DailyConsumption {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp()
  private LocalDateTime consumptionDate;

  @Column(nullable = false)
  private ProductType consumptionType;

  // Relations

  @ManyToOne()
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne()
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
