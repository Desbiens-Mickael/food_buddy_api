package fr.olprog_b.food_buddy.dto.establishmentAddress.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;

@Component
public class EstablishmentAddressResponseMapper {
  public static EstablishmentAddressResponseDTO convertToDto(EstablishmentAddress establishmentaddress) {
    return new EstablishmentAddressResponseDTO(
      establishmentaddress.getStreetNumber(),
      establishmentaddress.getStreetName(),
      establishmentaddress.getZipCode(),
      establishmentaddress.getCity(),
      establishmentaddress.getLatitude(),
      establishmentaddress.getLongitude()
    );
  }
}
