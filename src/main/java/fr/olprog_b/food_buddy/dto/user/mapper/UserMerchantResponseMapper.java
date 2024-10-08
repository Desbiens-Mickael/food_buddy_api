package fr.olprog_b.food_buddy.dto.user.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.business.mapper.BusinessResponseMapper;
import fr.olprog_b.food_buddy.dto.user.UserMerchantResponseDTO;
import fr.olprog_b.food_buddy.model.User;

@Component
public class UserMerchantResponseMapper {
  public static UserMerchantResponseDTO convertToDto(User user) {
    return new UserMerchantResponseDTO(
      user.getFirstname(),
      user.getLastname(),
      user.getProfileImageUrl(),
      user.getEmail(),
      // TODO : Fait
      BusinessResponseMapper.convertToDto(user.getBusiness())
    );
  }
}
