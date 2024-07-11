package fr.olprog_b.food_buddy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.user.PutUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
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

  @GetMapping("/me/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    Optional<UserResponseDTO> user = userService.getUserById(id);
    if (!user.isPresent()) {
      return ResponseEntity.notFound().build();
    }
  
    return ResponseEntity.ok(user.get());
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody PutUserDTO user) {
    Optional<UserResponseDTO> updatedUser = userService.updateUser(id, user);
    if (!updatedUser.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(updatedUser.get());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
    if (userService.deleteUser(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
