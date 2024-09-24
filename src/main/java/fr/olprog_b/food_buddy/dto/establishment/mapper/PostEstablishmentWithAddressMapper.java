package fr.olprog_b.food_buddy.dto.establishment.mapper;

import fr.olprog_b.food_buddy.dto.establishment.PostEstablishmentWithAddressDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.mapper.EstablishmentAddressResponseMapper;
import fr.olprog_b.food_buddy.model.Establishment;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;

public class PostEstablishmentWithAddressMapper {
  public static Establishment convertToEntity(PostEstablishmentWithAddressDTO establishmentWithAddress) {
    Establishment establishmentToAdd = EstablishmentResponseMapper.convertToEntity(establishmentWithAddress.establishment());
    EstablishmentAddress establishmentaddressToAdd = EstablishmentAddressResponseMapper.convertToEntity(establishmentWithAddress.address());
    establishmentToAdd.setAddress(establishmentaddressToAdd);
    establishmentaddressToAdd.setEstablishment(establishmentToAdd);
    return establishmentToAdd;
  }
}
