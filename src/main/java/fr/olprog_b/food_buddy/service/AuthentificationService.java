package fr.olprog_b.food_buddy.service;

import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.configuration.JwtService;
import fr.olprog_b.food_buddy.dto.authentification.LoginResponseDTO;
import fr.olprog_b.food_buddy.dto.authentification.mapper.LoginResponseMapper;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthentificationService {
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public AuthentificationService(
    UserRepository userRepository, 
    AuthenticationManager authenticationManager,
    JwtService jwtService
    ) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  // public Optional<User> login(String email, String password, HttpServletResponse response) {
  //   final Authentication authenticate = authenticationManager.authenticate(
  //     new UsernamePasswordAuthenticationToken(email, password));

  //     if (authenticate.isAuthenticated()) {
  //         User user = (User) authenticate.getPrincipal();
  //         Map<String, String> token = jwtService.generateToken(user.getEmail());
  //         ResponseCookie cookie = ResponseCookie.from("token", token.get("bearer"))
  //             .httpOnly(true)
  //             .secure(true)
  //             .path("/")
  //             .maxAge(7 * 24 * 60 * 60)
  //             .sameSite("Strict")
  //             .build();

  //         response.addHeader("Set-Cookie", cookie.toString());
  //         return Optional.of(user);
  //       }
  //   return null;
  // }

  public void logout(HttpServletResponse response) {
    ResponseCookie cookie = ResponseCookie.from("token", "")
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(0)
        .sameSite("Strict")
        .build();
    response.addHeader("Set-Cookie", cookie.toString());
  }
}
