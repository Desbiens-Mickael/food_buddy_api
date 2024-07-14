package fr.olprog_b.food_buddy.dto.product.mapper;

import fr.olprog_b.food_buddy.dto.product.PostProductDTO;
import fr.olprog_b.food_buddy.model.Product;

public class PostProductMapper {

  public Product convertToEntity(PostProductDTO postProductDTO) {
    Product product = new Product();
    product.setName(postProductDTO.name());
    product.setDescription(postProductDTO.description());
    product.setPrice(postProductDTO.price());
    product.setType(postProductDTO.type());
    product.setStatus(postProductDTO.status());
    product.setEstablishment(null);
    product.setAllergens(null);
    return product;
  }
}
