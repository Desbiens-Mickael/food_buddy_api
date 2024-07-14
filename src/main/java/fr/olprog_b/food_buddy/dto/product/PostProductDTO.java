package fr.olprog_b.food_buddy.dto.product;

import java.math.BigDecimal;

import fr.olprog_b.food_buddy.enums.ProductStatus;
import fr.olprog_b.food_buddy.enums.ProductType;

public class PostProductDTO {
  private String name;
  private String description;
  private BigDecimal price;
  private ProductType type;
  private ProductStatus status;
  private Long[] allergensIds;

  // Getters and setters
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  public ProductType getType() {
    return type;
  }
  public void setType(ProductType type) {
    this.type = type;
  }
  public ProductStatus getStatus() {
    return status;
  }
  public void setStatus(ProductStatus status) {
    this.status = status;
  }
  public Long[] getAllergensIds() {
    return allergensIds;
  }
  public void setAllergensIds(Long[] allergensIds) {
    this.allergensIds = allergensIds;
  }
}
