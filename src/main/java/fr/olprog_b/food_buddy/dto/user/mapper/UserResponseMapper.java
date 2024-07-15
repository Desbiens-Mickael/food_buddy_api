package fr.olprog_b.food_buddy.dto.user.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.model.User;

@Component
public class UserResponseMapper {
  public static UserResponseDTO convertToDTO(User user) {
    UserResponseDTO userDTO = new UserResponseDTO(
      user.getEmail(),
      user.getFirstname(),
      user.getLastname(),
      user.getProfileImageUrl(),
      user.getRole(),
      user.getIsEligible(),
      user.getCreatedAt(),
      user.getUpdatedAt()
    );
    return userDTO;
  } 
}
