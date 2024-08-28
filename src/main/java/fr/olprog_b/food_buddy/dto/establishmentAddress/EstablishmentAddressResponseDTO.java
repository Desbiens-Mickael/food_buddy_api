package fr.olprog_b.food_buddy.dto.establishmentAddress;


import fr.olprog_b.food_buddy.dto.business.PostBusinessDTO;
import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;

public record EstablishmentAddressResponseDTO(
  PostEstablishmentAddressDTO address,
  EstablishmentResponseDTO establishment,
  PostBusinessDTO business
) {}