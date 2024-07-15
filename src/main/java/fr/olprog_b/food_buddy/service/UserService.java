package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.user.PostUserDTO;
import fr.olprog_b.food_buddy.dto.user.PostUserMerchantDTO;
import fr.olprog_b.food_buddy.dto.user.PutUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserMerchantResponseDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.dto.user.mapper.PostUserMapper;
import fr.olprog_b.food_buddy.dto.user.mapper.PostUserMerchantMapper;
import fr.olprog_b.food_buddy.dto.user.mapper.UserMerchantResponseMapper;
import fr.olprog_b.food_buddy.dto.user.mapper.UserResponseMapper;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PostUserMapper postUserMapper;
  private final PostUserMerchantMapper postUserMerchantMapper;
  

  public UserService(UserRepository userRepository, PostUserMapper postUserMapper, PostUserMerchantMapper postUserMerchantMapper) {
    this.userRepository = userRepository;
    this.postUserMapper = postUserMapper;
    this.postUserMerchantMapper = postUserMerchantMapper;
  }

  public UserResponseDTO createUser(PostUserDTO userDTO) {
    User user = userRepository.save(postUserMapper.convertToEntity(userDTO));
    return UserResponseMapper.convertToDTO(user);
  }

  @Transactional
  public UserMerchantResponseDTO createUserMerchant(PostUserMerchantDTO userDTO) {
    User user = userRepository.save(postUserMerchantMapper.convertToEntity(userDTO));
    return UserMerchantResponseMapper.convertToDto(user);
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

  public Optional<UserResponseDTO> updateUser(Long id, PutUserDTO putUserDTO) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      return Optional.empty();
    }
    User user = optionalUser.get();
    user.setEmail(putUserDTO.email());
    user.setFirstname(putUserDTO.firstname());
    user.setLastname(putUserDTO.lastname());
    user.setProfileImageUrl(putUserDTO.profileImageUrl());
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
