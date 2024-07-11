package fr.olprog_b.food_buddy.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.authentification.LoginDTO;
import fr.olprog_b.food_buddy.dto.authentification.LoginResponseDTO;
import fr.olprog_b.food_buddy.dto.user.PostUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.AuthentificationService;
import fr.olprog_b.food_buddy.service.UserService;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthentificationService authentificationService;

    public AuthController(UserService userService, AuthentificationService authentificationService) {
        this.userService = userService;
        this.authentificationService = authentificationService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody PostUserDTO newUser) {
        UserResponseDTO createdUser = userService.createUser(newUser);
        if (createdUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @PostMapping("/merchant/register") // Lazhar
    public void registerMerchant(@RequestBody User user) {
        //TODO: Mise en place de la création d'un commerçant
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<LoginResponseDTO>> login(@RequestBody LoginDTO credentials) {
        LoginResponseDTO loginResponseDTO = authentificationService.validateCredentials(credentials.getEmail(), credentials.getPassword());
        if (loginResponseDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(Optional.of(loginResponseDTO));
    }

    @PostMapping("/logout/{id}")
    public void logout(@RequestParam Long id) {
        //TODO: Mise en place de la déconnexion d'un utilisateur        
    }
}
