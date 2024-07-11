package fr.olprog_b.food_buddy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.authentification.LoginResponseDTO;
import fr.olprog_b.food_buddy.dto.authentification.mapper.LoginResponseMapper;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.repository.UserRepository;

@Service
public class AuthentificationService {
  private final UserRepository userRepository;

  public AuthentificationService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public LoginResponseDTO validateCredentials(String email, String password) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) {
      return null;
    }
    if (!user.get().getPassword().equals(password)) {
      return null;
    }
    return LoginResponseMapper.convertToDTO(user.get());
  }
}
