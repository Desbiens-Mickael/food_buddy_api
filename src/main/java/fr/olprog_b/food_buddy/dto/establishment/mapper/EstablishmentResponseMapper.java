package fr.olprog_b.food_buddy.dto.establishment.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.mapper.EstablishmentAddressResponseMapper;
import fr.olprog_b.food_buddy.model.Establishment;

@Component
public class EstablishmentResponseMapper {
  public static EstablishmentResponseDTO convertToDto(Establishment establishment) {
    return new EstablishmentResponseDTO(
      establishment.getName(),
      establishment.getSiret(),
      establishment.getEmail(),
      establishment.getPhoneNumber(),
      EstablishmentAddressResponseMapper.convertToDto(establishment.getAddress())
    );
  }
}
