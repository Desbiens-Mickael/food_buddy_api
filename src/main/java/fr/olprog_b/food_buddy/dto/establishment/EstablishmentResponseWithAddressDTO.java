package fr.olprog_b.food_buddy.dto.establishment;

import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;

public record EstablishmentResponseWithAddressDTO(
  String name,
  String siret,
  String email,
  String phoneNumber,
  EstablishmentAddressResponseDTO address
) {}
