package fr.olprog_b.food_buddy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.olprog_b.food_buddy.dto.authentification.LoginResponseDTO;
import fr.olprog_b.food_buddy.dto.user.PutUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.UserService;
import fr.olprog_b.food_buddy.service.UploadImageService;
@RestController
@RequestMapping("/users")
public class UserController {
  private final String AVATAR_ROOT_DIR = "upload-dir/avatar";
  private final UserService userService;
  private final UploadImageService uploadImageService;

  public UserController(UserService userService, UploadImageService uploadImageService) {
    this.userService = userService;
    this.uploadImageService = uploadImageService;
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
  public ResponseEntity<LoginResponseDTO> getUserById(@AuthenticationPrincipal User user) {
    Optional<LoginResponseDTO> optionalUser = userService.getUserById(user.getId());
    if (!optionalUser.isPresent()) {
      return ResponseEntity.notFound().build();
    }
  
    return ResponseEntity.ok(optionalUser.get());
  }

  // Upload avatar
  @PostMapping("/upload-avatar/{userEmail}")
  public ResponseEntity<Optional<UserResponseDTO>> handleFileUpload(@PathVariable String userEmail, @RequestBody MultipartFile file) {
      try {
            String avatarUrl = uploadImageService.uploadImage(AVATAR_ROOT_DIR, file);
            if (avatarUrl != null) {
                return ResponseEntity.ok(userService.patchAvatarUser(userEmail, avatarUrl));
            }
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
      }
  }

  @GetMapping("/avatar/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
      try {
          Resource resource = uploadImageService.getImage(AVATAR_ROOT_DIR, filename);
          if (resource == null) {
              return ResponseEntity.notFound().build();
          }

          return ResponseEntity.ok()
                  .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                  .body(resource);
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
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
