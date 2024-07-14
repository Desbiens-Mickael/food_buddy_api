package fr.olprog_b.food_buddy.dto.user;

import java.time.LocalDateTime;

import fr.olprog_b.food_buddy.enums.UserRole;

public record UserResponseDTO(
  String email,
  String firstname,
  String lastname,
  String profileImageUrl,
  UserRole role,
  Boolean isEligible,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {}
