package fr.olprog_b.food_buddy.dto.allergen.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.allergen.PostAllergenDTO;
import fr.olprog_b.food_buddy.model.Allergen;

@Component
public class PostAllergenMapper {

  public static Allergen convertToEntity(PostAllergenDTO postAllergenDTO) {
    Allergen allergen = new Allergen();
    allergen.setName(postAllergenDTO.name());
    return allergen;
  }
}
