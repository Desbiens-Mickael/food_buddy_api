package fr.olprog_b.food_buddy.dto.user.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.user.PostUserDTO;
import fr.olprog_b.food_buddy.model.User;

@Component
public class PostUserMapper {

  public static User convertToEntity(PostUserDTO userDTO) {
    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setFirstname(userDTO.getFirstname());
    user.setLastname(userDTO.getLastname());
    user.setProfileImageUrl(userDTO.getProfileImageUrl());
    user.setPassword(userDTO.getPassword());
    return user;
  }
}
