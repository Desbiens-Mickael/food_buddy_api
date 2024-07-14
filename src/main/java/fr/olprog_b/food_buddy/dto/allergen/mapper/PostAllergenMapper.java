package fr.olprog_b.food_buddy.dto.allergen.mapper;

import fr.olprog_b.food_buddy.dto.allergen.PostAllergenDTO;
import fr.olprog_b.food_buddy.model.Allergen;

public class PostAllergenMapper {

  public static Allergen convertToEntity(PostAllergenDTO postAllergenDTO) {
    Allergen allergen = new Allergen();
    allergen.setName(postAllergenDTO.name());
    return allergen;
  }
}
