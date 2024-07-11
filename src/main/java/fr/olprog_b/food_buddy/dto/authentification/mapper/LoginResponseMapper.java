package fr.olprog_b.food_buddy.dto.authentification.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.authentification.LoginResponseDTO;
import fr.olprog_b.food_buddy.model.User;

@Component
  public class LoginResponseMapper {
    public static LoginResponseDTO convertToDTO(User user) {
      LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
      loginResponseDTO.setEmail(user.getEmail());
      loginResponseDTO.setFirstname(user.getFirstname());
      loginResponseDTO.setLastname(user.getLastname());
      loginResponseDTO.setProfileImageUrl(user.getProfileImageUrl());
      loginResponseDTO.setPassword(user.getPassword());
      loginResponseDTO.setRole(user.getRole());
      loginResponseDTO.setIsEligible(user.getIsEligible());
      return loginResponseDTO;
    }
  }
