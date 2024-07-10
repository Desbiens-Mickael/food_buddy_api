package fr.olprog_b.food_buddy.model;

import java.time.LocalDateTime;

import fr.olprog_b.food_buddy.enums.ProductType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name = "daily_consumptions")
public class DailyConsumption {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
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

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getConsumptionDate() {
    return consumptionDate;
  }

  public void setConsumptionDate(LocalDateTime consumptionDate) {
    this.consumptionDate = consumptionDate;
  }

  public ProductType getConsumptionType() {
    return consumptionType;
  }

  public void setConsumptionType(ProductType consumptionType) {
    this.consumptionType = consumptionType;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
