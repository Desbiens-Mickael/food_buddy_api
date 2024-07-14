package fr.olprog_b.food_buddy.service;


import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthentificationService {


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
