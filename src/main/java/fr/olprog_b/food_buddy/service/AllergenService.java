package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.allergen.AllergenResponseDTO;
import fr.olprog_b.food_buddy.dto.allergen.PostAllergenDTO;
import fr.olprog_b.food_buddy.dto.allergen.mapper.AllergenResponseMapper;
import fr.olprog_b.food_buddy.dto.allergen.mapper.PostAllergenMapper;
import fr.olprog_b.food_buddy.model.Allergen;
import fr.olprog_b.food_buddy.repository.AllergenRepository;

@Service
public class AllergenService {
  private final AllergenRepository allergenRepository;

  public AllergenService(AllergenRepository allergenRepository) {
    this.allergenRepository = allergenRepository;
  }

  public AllergenResponseDTO createAllergen(PostAllergenDTO allergenDTO) {
    Allergen allergen = allergenRepository.save(PostAllergenMapper.convertToEntity(allergenDTO));
    return AllergenResponseMapper.convertToDto(allergen);
  }

  public List<AllergenResponseDTO> getAllAllergen() {
    return allergenRepository.findAll().stream().map(AllergenResponseMapper::convertToDto).collect(Collectors.toList());
  }

  public AllergenResponseDTO getAllergenById(Long id) {
    Optional<Allergen> optionalAllergen = allergenRepository.findById(id);
    if (!optionalAllergen.isPresent()) {
      return null;
    }

    return AllergenResponseMapper.convertToDto(optionalAllergen.get());
  }

  public AllergenResponseDTO updateAllergen(Long id, PostAllergenDTO allergenDTO) {
    Optional<Allergen> optionalAllergen = allergenRepository.findById(id);
    if (!optionalAllergen.isPresent()) {
      return null;
    }
    Allergen allergen = optionalAllergen.get();
    allergen.setName(allergenDTO.name());
    Allergen updatedAllergen = allergenRepository.save(allergen);

    return AllergenResponseMapper.convertToDto(updatedAllergen);
  }

  public Boolean delete(Long id) {
    Optional<Allergen> optionalAllergen = allergenRepository.findById(id);
    if (!optionalAllergen.isPresent()) {
      return false;
    }

    allergenRepository.delete(optionalAllergen.get());
    return true;
  }
}
