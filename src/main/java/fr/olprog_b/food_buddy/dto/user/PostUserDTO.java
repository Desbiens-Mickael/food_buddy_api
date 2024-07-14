package fr.olprog_b.food_buddy.dto.user;

public record PostUserDTO(
  String firstname,
  String lastname,
  String profileImageUrl,
  String email,
  String password
) {}
