package fr.olprog_b.food_buddy.dto.user.mapper;

import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.model.User;

public class UserResponseMapper {
  public static UserResponseDTO convertToDTO(User user) {
    UserResponseDTO userDTO = new UserResponseDTO();
    userDTO.setId(user.getId());
    userDTO.setEmail(user.getEmail());
    userDTO.setFirstname(user.getFirstname());
    userDTO.setLastname(user.getLastname());
    userDTO.setProfileImageUrl(user.getProfileImageUrl());
    userDTO.setPassword(user.getPassword());
    userDTO.setRole(user.getRole());
    userDTO.setIsEligible(user.getIsEligible());
    userDTO.setCreatedAt(user.getCreatedAt());
    userDTO.setUpdatedAt(user.getUpdatedAt());
    return userDTO;
  } 
}
