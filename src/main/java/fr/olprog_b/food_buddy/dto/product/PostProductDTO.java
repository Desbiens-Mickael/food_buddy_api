package fr.olprog_b.food_buddy.dto.product;

import java.math.BigDecimal;
import java.util.List;

import fr.olprog_b.food_buddy.enums.ProductStatus;
import fr.olprog_b.food_buddy.enums.ProductType;

public record PostProductDTO(
  String name,
  String description,
  BigDecimal price,
  ProductType type,
  ProductStatus status,
  List<Long> allergensIds
) {}
