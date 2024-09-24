package fr.olprog_b.food_buddy.dto.establishmentAddress;

import java.math.BigDecimal;

public record AddressResponseDTO(
  Long id,
  Integer streetNumber,
  String streetName,
  String zipCode,
  String city,
  BigDecimal latitude,
  BigDecimal longitude  
) {

}
