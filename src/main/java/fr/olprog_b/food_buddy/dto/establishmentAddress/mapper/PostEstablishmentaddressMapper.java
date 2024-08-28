package fr.olprog_b.food_buddy.dto.establishmentAddress.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.establishmentAddress.PostEstablishmentAddressDTO;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;

@Component
public class PostEstablishmentaddressMapper {
  public static EstablishmentAddress convertToEntity(PostEstablishmentAddressDTO postEstablishmentaddressDTO) {
    EstablishmentAddress establishmentaddress = new EstablishmentAddress();
    establishmentaddress.setStreetNumber(postEstablishmentaddressDTO.streetNumber());
    establishmentaddress.setStreetName(postEstablishmentaddressDTO.streetName());
    establishmentaddress.setZipCode(postEstablishmentaddressDTO.zipCode());
    establishmentaddress.setCity(postEstablishmentaddressDTO.city());
    establishmentaddress.setLatitude(postEstablishmentaddressDTO.latitude());
    establishmentaddress.setLongitude(postEstablishmentaddressDTO.longitude());
    return establishmentaddress;
  }

  public static PostEstablishmentAddressDTO convertToDto(EstablishmentAddress establishmentaddress) {
    return new PostEstablishmentAddressDTO(
      establishmentaddress.getStreetNumber(),
      establishmentaddress.getStreetName(),
      establishmentaddress.getZipCode(),
      establishmentaddress.getCity(),
      establishmentaddress.getLatitude(),
      establishmentaddress.getLongitude()
    );
  }
}
