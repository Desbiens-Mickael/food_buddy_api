package fr.olprog_b.food_buddy.dto.business;

import java.util.List;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseWithAddressDTO;

public record BusinessResponseDTO(
  String name,
  String siren,
  String logoUrl,
  List<EstablishmentResponseWithAddressDTO> establishments
) {}
