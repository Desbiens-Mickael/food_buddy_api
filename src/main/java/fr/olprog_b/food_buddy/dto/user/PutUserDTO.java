package fr.olprog_b.food_buddy.dto.user;

public record PutUserDTO(
  String email,
  String firstname,
  String lastname,
  String profileImageUrl
) {}
