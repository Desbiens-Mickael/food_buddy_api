package fr.olprog_b.food_buddy.dto.allergen.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.allergen.AllergenResponseDTO;
import fr.olprog_b.food_buddy.model.Allergen;

@Component
public class AllergenResponseMapper {
  public static AllergenResponseDTO convertToDto(Allergen allergen) {
    AllergenResponseDTO allergenResponseDTO = new AllergenResponseDTO(
      allergen.getId(),
      allergen.getName()  
    );
  
    return allergenResponseDTO;
  }
}
