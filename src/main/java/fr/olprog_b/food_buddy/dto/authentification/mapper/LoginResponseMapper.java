package fr.olprog_b.food_buddy.dto.authentification.mapper;

import java.util.Optional;

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
      loginResponseDTO.setRole(user.getRole());
      loginResponseDTO.setIsEligible(user.getIsEligible());

      if (!user.getBusinesses().isEmpty()) {
          loginResponseDTO.setBusinesseName(Optional.ofNullable(user.getBusinesses().get(0).getName()));
          loginResponseDTO.setBusinessLogoUrl(Optional.ofNullable(user.getBusinesses().get(0).getLogoUrl()));
      } else {
          loginResponseDTO.setBusinesseName(Optional.empty());
          loginResponseDTO.setBusinessLogoUrl(Optional.empty());
      }
      return loginResponseDTO;
    }
  }
