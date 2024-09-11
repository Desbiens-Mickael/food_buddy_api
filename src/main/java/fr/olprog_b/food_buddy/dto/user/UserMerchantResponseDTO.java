package fr.olprog_b.food_buddy.dto.user;

import fr.olprog_b.food_buddy.dto.business.BusinessResponseDTO;
public record UserMerchantResponseDTO(
  String firstname,
  String lastname,
  String profileImageUrl,
  String email,
  BusinessResponseDTO business
) {}
