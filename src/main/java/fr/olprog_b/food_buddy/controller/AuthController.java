package fr.olprog_b.food_buddy.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.user.PostUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.UserService;

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

    public AuthController(UserService userService) {
        this.userService = userService;
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
    public User registerMerchant(@RequestBody User user) {
        //TODO: Mise en place de la création d'un commerçant
        
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody String credentials) {
        //TODO: Mise en place de la vérification des identifiants et mot de passe
        
        return "User logged in";
    }

    @PostMapping("/logout/{id}")
    public String logout(@RequestParam Long id) {
        //TODO: Mise en place de la déconnexion d'un utilisateur
        
        return "User logged out";
    }
  
}
