package fr.olprog_b.food_buddy.dto.establishment;


import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;

public record EstablishmentResponseDTO(
  String name,
  String siret,
  String email,
  String phoneNumber,
  EstablishmentAddressResponseDTO address
) {}
