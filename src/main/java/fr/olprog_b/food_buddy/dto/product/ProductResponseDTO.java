package fr.olprog_b.food_buddy.dto.product;

import java.math.BigDecimal;

import fr.olprog_b.food_buddy.enums.ProductStatus;
import fr.olprog_b.food_buddy.enums.ProductType;

public record ProductResponseDTO(
  String name,
  String description,
  BigDecimal price,
  ProductType type,
  ProductStatus status,
  // AllergenDTO[] allergens,
  Long establishmentId
) {
}
