package fr.olprog_b.food_buddy.dto.establishment;

import fr.olprog_b.food_buddy.dto.establishmentAddress.PostEstablishmentAddressDTO;

public record PostEstablishmentWithAddressDTO(
  PostEstablishmentDTO establishment,
  PostEstablishmentAddressDTO address
) {
}
