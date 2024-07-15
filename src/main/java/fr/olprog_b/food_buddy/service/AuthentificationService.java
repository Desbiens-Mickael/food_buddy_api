package fr.olprog_b.food_buddy.service;


import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.model.Establishment;
import fr.olprog_b.food_buddy.repository.EstablishmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class AuthentificationService {
  private final EstablishmentRepository establishmentRepository; 

  public AuthentificationService(
    EstablishmentRepository establishmentRepository
    ) {
    this.establishmentRepository = establishmentRepository;
  }

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

    @Transactional
  public Boolean isAuthorEstablisment(Long userId, Long establishmentId) {
    Establishment establishment = establishmentRepository.findById(establishmentId).orElse(null);
    if (establishment == null) {
      return false;
    }
    
    return establishment.getBusiness().getUser().getId().equals(userId);
  }
}
