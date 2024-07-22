package fr.olprog_b.food_buddy.dto.establishment.mapper;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseWithAddressDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.mapper.EstablishmentAddressResponseMapper;
import fr.olprog_b.food_buddy.model.Establishment;

public class EstablishmentResponseWithAddressMapper {
   public static EstablishmentResponseWithAddressDTO convertToDto(Establishment establishment) {
    return new EstablishmentResponseWithAddressDTO(
      establishment.getName(),
      establishment.getSiret(),
      establishment.getEmail(),
      establishment.getPhoneNumber(),
      EstablishmentAddressResponseMapper.convertToDto(establishment.getAddress())
    );
  }
}
