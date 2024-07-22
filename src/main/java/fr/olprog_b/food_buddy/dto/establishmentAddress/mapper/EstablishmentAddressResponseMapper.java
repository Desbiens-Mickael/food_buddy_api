package fr.olprog_b.food_buddy.dto.establishmentAddress.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.business.PostBusinessDTO;
import fr.olprog_b.food_buddy.dto.business.mapper.PostBusinessMapper;
import fr.olprog_b.food_buddy.dto.establishment.mapper.EstablishmentResponseMapper;
import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.PostEstablishmentAddressDTO;
import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;

@Component
public class EstablishmentAddressResponseMapper {
  public static EstablishmentAddressResponseDTO convertToDto(EstablishmentAddress establishmentaddress) {
    PostEstablishmentAddressDTO address = PostEstablishmentaddressMapper.convertToDto(establishmentaddress);
    EstablishmentResponseDTO establishment = EstablishmentResponseMapper.convertToDto(establishmentaddress.getEstablishment());
    PostBusinessDTO  business = PostBusinessMapper.convertToDto(establishmentaddress.getEstablishment().getBusiness());
    
    return new EstablishmentAddressResponseDTO(
      address,
      establishment,
      business
    );
  }
}
