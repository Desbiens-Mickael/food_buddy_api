package fr.olprog_b.food_buddy.dto.product.mapper;

import fr.olprog_b.food_buddy.dto.product.ProductResponseDTO;

public class ProductResponseMapper {
  public static ProductResponseDTO convertToDTO(fr.olprog_b.food_buddy.model.Product product) {
    return new ProductResponseDTO(
      product.getName(),
      product.getDescription(),
      product.getPrice(),
      product.getType(),
      product.getStatus(),
      product.getEstablishment().getId()
    );
  }
}
