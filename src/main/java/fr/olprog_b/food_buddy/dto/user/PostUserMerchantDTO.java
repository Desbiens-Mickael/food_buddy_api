package fr.olprog_b.food_buddy.dto.user;

import fr.olprog_b.food_buddy.dto.business.PostBusinessDTO;
import fr.olprog_b.food_buddy.dto.establishment.PostEstablishmentDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.PostEstablishmentAddressDTO;

public record PostUserMerchantDTO(
  PostUserDTO newUser,
  PostBusinessDTO business,
  PostEstablishmentDTO establishment,
  PostEstablishmentAddressDTO address
) {}
