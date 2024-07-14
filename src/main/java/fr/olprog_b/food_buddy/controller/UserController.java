package fr.olprog_b.food_buddy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.user.PutUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping()
  public ResponseEntity<List<UserResponseDTO>> getAllUser() {
    List<UserResponseDTO> users = userService.getAllUsers();
    if (users.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(users);
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponseDTO> getUserById(@AuthenticationPrincipal User user) {
    Optional<UserResponseDTO> optionalUser = userService.getUserById(user.getId());
    if (!optionalUser.isPresent()) {
      return ResponseEntity.notFound().build();
    }
  
    return ResponseEntity.ok(optionalUser.get());
  }
  
  @PutMapping()
  public ResponseEntity<UserResponseDTO> updateUser(@AuthenticationPrincipal User user, @RequestBody PutUserDTO putUserDTO) {
    Optional<UserResponseDTO> updatedUser = userService.updateUser(user.getId(), putUserDTO);
    if (!updatedUser.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(updatedUser.get());
  }

  @DeleteMapping()
  public ResponseEntity<Boolean> deleteUser(@AuthenticationPrincipal User user) {
    if (userService.deleteUser(user.getId())) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
