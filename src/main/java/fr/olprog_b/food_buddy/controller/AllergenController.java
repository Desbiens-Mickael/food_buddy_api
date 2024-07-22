package fr.olprog_b.food_buddy.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.allergen.AllergenResponseDTO;
import fr.olprog_b.food_buddy.dto.allergen.PostAllergenDTO;
import fr.olprog_b.food_buddy.service.AllergenService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("allergens")
public class AllergenController {
  private final AllergenService allergenService;

  public AllergenController(AllergenService allergenService) {
    this.allergenService = allergenService;
  }
  
  @PostMapping()
  public ResponseEntity<AllergenResponseDTO> postMethodName(@RequestBody PostAllergenDTO allergenDTO) {
      AllergenResponseDTO allergenResponseDTO = allergenService.createAllergen(allergenDTO);
      if (allergenResponseDTO == null) {
          return ResponseEntity.badRequest().build();
      }
      return ResponseEntity.status(HttpStatus.CREATED).body(allergenResponseDTO);
  }

  @GetMapping()
  public ResponseEntity<List<AllergenResponseDTO>> getAllAllergen() {
      List<AllergenResponseDTO> allergenResponseDTO = allergenService.getAllAllergen();
      if (allergenResponseDTO == null) {
          return ResponseEntity.badRequest().build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(allergenResponseDTO);
  }
}


