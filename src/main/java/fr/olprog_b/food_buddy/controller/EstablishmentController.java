package fr.olprog_b.food_buddy.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.EstablishmentService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("establishments")
public class EstablishmentController {
  private final EstablishmentService establishmentService;

  public EstablishmentController(EstablishmentService establishmentService) {
    this.establishmentService = establishmentService;
  }
  
  @GetMapping
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<List<EstablishmentResponseDTO>> getAllEstablishment(@AuthenticationPrincipal User user) {
    List<EstablishmentResponseDTO> establishmentResponseDTO = establishmentService.getAllEstablishment(user.getId());
    if (establishmentResponseDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(establishmentResponseDTO);
  }
}
