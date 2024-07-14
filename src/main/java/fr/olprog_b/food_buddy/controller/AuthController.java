package fr.olprog_b.food_buddy.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.configuration.JwtService;
import fr.olprog_b.food_buddy.dto.authentification.LoginDTO;
import fr.olprog_b.food_buddy.dto.authentification.LoginResponseDTO;
import fr.olprog_b.food_buddy.dto.authentification.mapper.LoginResponseMapper;
import fr.olprog_b.food_buddy.dto.user.PostUserDTO;
import fr.olprog_b.food_buddy.dto.user.UserResponseDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.AuthentificationService;
// import fr.olprog_b.food_buddy.service.AuthentificationService;
import fr.olprog_b.food_buddy.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthentificationService authentificationService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(
        UserService userService, 
        AuthentificationService authentificationService, 
        JwtService jwtService, 
        AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.authentificationService = authentificationService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody PostUserDTO newUser) {
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
    public ResponseEntity<Optional<LoginResponseDTO>> login(@Valid @RequestBody LoginDTO credentials , HttpServletResponse response) {
        try {
            final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));

            if (authenticate.isAuthenticated()) {
                User user = (User) authenticate.getPrincipal();
                Map<String, String> token = jwtService.generateToken(user.getEmail());
                ResponseCookie cookie = ResponseCookie.from("token", token.get("bearer"))
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60)
                    .sameSite("Strict")
                    .build();

                response.addHeader("Set-Cookie", cookie.toString());
                return ResponseEntity.status(HttpStatus.OK).body(Optional.of(LoginResponseMapper.convertToDTO(user)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Je ne vous connais pas!!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal User user, HttpServletResponse response) {
        if (user != null) {
            authentificationService.logout(response);
            return ResponseEntity.ok("Déconnexion réussie");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun utilisateur authentifié");
        }
    }
}
