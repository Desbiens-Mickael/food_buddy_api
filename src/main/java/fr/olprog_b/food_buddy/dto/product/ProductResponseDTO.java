package fr.olprog_b.food_buddy.dto.product;

import java.math.BigDecimal;
import java.util.List;

import fr.olprog_b.food_buddy.dto.allergen.AllergenResponseDTO;
import fr.olprog_b.food_buddy.enums.ProductStatus;
import fr.olprog_b.food_buddy.enums.ProductType;

public record ProductResponseDTO(
  Long id,
  String name,
  String description,
  BigDecimal price,
  ProductType type,
  ProductStatus status,
  String imageUrl,
  Integer numberAvailable,
  Integer numberReservations,
  Long establishmentId,
  List<AllergenResponseDTO> allergens
) {
}
