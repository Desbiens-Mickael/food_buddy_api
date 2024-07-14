package fr.olprog_b.food_buddy.dto.product.mapper;

import fr.olprog_b.food_buddy.dto.product.PostProductDTO;
import fr.olprog_b.food_buddy.model.Product;

public class PostProductMapper {

  public Product convertToEntity(PostProductDTO postProductDTO) {
    Product product = new Product();
    product.setName(postProductDTO.getName());
    product.setDescription(postProductDTO.getDescription());
    product.setPrice(postProductDTO.getPrice());
    product.setType(postProductDTO.getType());
    product.setStatus(postProductDTO.getStatus());
    product.setEstablishment(null);
    product.setAllergens(null);
    return product;
  }
}
