package fr.olprog_b.food_buddy.dto.business;

public record BusinessResponseDTO(
  Long id,
  String name,
  String siren,
  String logoUrl
  // List<EstablishmentResponseWithAddressDTO> establishments
) {}
