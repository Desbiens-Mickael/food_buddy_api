package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.user.PostUserDTO;
import fr.olprog_b.food_buddy.dto.user.PutUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.dto.user.mapper.PostUserMapper;
import fr.olprog_b.food_buddy.dto.user.mapper.UserResponseMapper;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserResponseDTO createUser(PostUserDTO userDTO) {
    User user = userRepository.save(PostUserMapper.convertToEntity(userDTO));
    return UserResponseMapper.convertToDTO(user);
  }

  public Optional<UserResponseDTO> getUserById(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(UserResponseMapper.convertToDTO(optionalUser.get()));
  }

  public List<UserResponseDTO> getAllUsers() {
    return userRepository.findAll().stream().map(UserResponseMapper::convertToDTO).collect(Collectors.toList());
  }

  public Optional<UserResponseDTO> updateUser(Long id, PutUserDTO userDTO) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      return Optional.empty();
    }
    User user = optionalUser.get();
    user.setEmail(userDTO.getEmail());
    user.setFirstname(userDTO.getFirstname());
    user.setLastname(userDTO.getLastname());
    user.setProfileImageUrl(userDTO.getProfileImageUrl());
    User updatedUser = userRepository.save(user);

    return Optional.of(UserResponseMapper.convertToDTO(updatedUser));
  }

  public Boolean deleteUser(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      return false;
    }

    userRepository.delete(optionalUser.get());
    return true;
  }
}
