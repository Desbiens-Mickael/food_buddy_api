package fr.olprog_b.food_buddy.dto.product.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.allergen.mapper.AllergenResponseMapper;
import fr.olprog_b.food_buddy.dto.product.ProductResponseDTO;

@Component
public class ProductResponseMapper {
  public static ProductResponseDTO convertToDTO(fr.olprog_b.food_buddy.model.Product product) {
    return new ProductResponseDTO(
      product.getName(),
      product.getDescription(),
      product.getPrice(),
      product.getType(),
      product.getStatus(),
      product.getEstablishment().getId(),
      product.getAllergens().stream().map(AllergenResponseMapper::convertToDto).collect(Collectors.toList())
    );
  }
}
