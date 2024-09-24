package fr.olprog_b.food_buddy.dto.establishmentAddress.mapper;

import fr.olprog_b.food_buddy.dto.establishmentAddress.AddressResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.PostEstablishmentAddressDTO;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;

public class AddressResponseMapper {
  public static AddressResponseDTO convertToDto(EstablishmentAddress establishmentaddress) {
    return new AddressResponseDTO(
      establishmentaddress.getId(),
      establishmentaddress.getStreetNumber(),
      establishmentaddress.getStreetName(),
      establishmentaddress.getZipCode(),
      establishmentaddress.getCity(),
      establishmentaddress.getLatitude(),
      establishmentaddress.getLongitude()
    );
  }

  public static EstablishmentAddress convertToEntity(PostEstablishmentAddressDTO addressResponseDTO) {
    EstablishmentAddress establishmentaddress = new EstablishmentAddress();
    establishmentaddress.setStreetNumber(addressResponseDTO.streetNumber());
    establishmentaddress.setStreetName(addressResponseDTO.streetName());
    establishmentaddress.setZipCode(addressResponseDTO.zipCode());
    establishmentaddress.setCity(addressResponseDTO.city());
    establishmentaddress.setLatitude(addressResponseDTO.latitude());
    establishmentaddress.setLongitude(addressResponseDTO.longitude());
    return establishmentaddress;
  }
}
