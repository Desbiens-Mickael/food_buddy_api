package fr.olprog_b.food_buddy.dto.establishment;


public record EstablishmentResponseDTO(
  Long id,
  String name,
  String siret,
  String email,
  String phoneNumber,
  String businessName,
  String logoBusinessUrl
) {}
