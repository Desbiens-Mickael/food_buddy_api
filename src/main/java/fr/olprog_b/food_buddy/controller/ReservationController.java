package fr.olprog_b.food_buddy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.reservation.ReservationResponseDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.AuthentificationService;
import fr.olprog_b.food_buddy.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
  private final AuthentificationService authentificationService;
  private final ReservationService reservationService;

  public ReservationController(
    AuthentificationService authentificationService, 
    ReservationService reservationService
    ) {
      this.authentificationService = authentificationService;
      this.reservationService = reservationService;
  }

  @PostMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_USER') and #user.isEligible")
  public ResponseEntity<ReservationResponseDTO> createReservation(@AuthenticationPrincipal User user, @PathVariable Long productId) {
    ReservationResponseDTO reservationResponseDTO = reservationService.createReservation(user, productId);
    if (reservationResponseDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(reservationResponseDTO);
  }

  // Retourne la liste des Réservations de l'utilisateur
  @GetMapping("/users")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<List<ReservationResponseDTO>> getReservations(@AuthenticationPrincipal User user) {
    List<ReservationResponseDTO> reservationDTOs = reservationService.findByUser(user);
    if (reservationDTOs == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(reservationDTOs);
  }

  // Retourne le nombre de réservations pour un produit
  @GetMapping("/establishments/{establishmentId}/products/{productId}")
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<Integer> getNumberOfReservationsByProduct(@AuthenticationPrincipal User user, @PathVariable Long productId, @PathVariable Long establishmentId) {
    if (!authentificationService.isAuthorEstablisment(user.getId(), establishmentId)) {
      return ResponseEntity.status(401).build();
    }
    Integer numberOfReservations = reservationService.getNumberOfReservationsByProduct(productId);

    return ResponseEntity.ok(numberOfReservations);
  }

  // Retourne la Réservation par code de validation
  @GetMapping("/establishments/{establishmentId}/search/{validationCode}")
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<ReservationResponseDTO> getReservationByValidationCode(
    @AuthenticationPrincipal User user, 
    @PathVariable Long establishmentId, 
    @PathVariable String validationCode
    ) {
    if (!authentificationService.isAuthorEstablisment(user.getId(), establishmentId)) {
      return ResponseEntity.status(401).build();
    }
    ReservationResponseDTO reservationResponseDTO = reservationService.getByValidationCode(validationCode);
    if (reservationResponseDTO == null) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(reservationResponseDTO);
  }

  @DeleteMapping("/{reservationId}/code/{validationCode}")
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<String> deleteReservation(@PathVariable Long reservationId, @PathVariable String validationCode) {
    if (!reservationService.deleteReservation(reservationId, validationCode)) {
      return ResponseEntity.badRequest().build();
    }
    return  ResponseEntity.ok().build();
  }
}
