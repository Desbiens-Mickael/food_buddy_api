package fr.olprog_b.food_buddy.dto.user.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.user.PostUserDTO;
import fr.olprog_b.food_buddy.model.User;

@Component
public class PostUserMapper {
  private final PasswordEncoder passwordEncoder;

  public PostUserMapper(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User convertToEntity(PostUserDTO userDTO) {
    User user = new User();
    user.setEmail(userDTO.email());
    user.setFirstname(userDTO.firstname());
    user.setLastname(userDTO.lastname());
    user.setProfileImageUrl(userDTO.profileImageUrl());
    user.setPassword(this.passwordEncoder.encode(userDTO.password()));
    return user;
  }
}
