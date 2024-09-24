package fr.olprog_b.food_buddy.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;
import fr.olprog_b.food_buddy.dto.establishment.PostEstablishmentDTO;
import fr.olprog_b.food_buddy.dto.establishment.PostEstablishmentWithAddressDTO;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.EstablishmentService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PutMapping("/{establishmentId}")
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<EstablishmentResponseDTO> updateEstablishment(
    @AuthenticationPrincipal User user, 
    @PathVariable Long establishmentId,
    @RequestBody PostEstablishmentDTO establishment
  ) {
    EstablishmentResponseDTO establishmentResponseDTO = establishmentService.updateEstablishment(establishment, establishmentId);
    if (establishmentResponseDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(establishmentResponseDTO);
  }

  @PostMapping()
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<EstablishmentResponseDTO > addEstablishment(
    @AuthenticationPrincipal User user,
    @RequestBody PostEstablishmentWithAddressDTO postEstablishmentWithAddressDTO
  ) {
    EstablishmentResponseDTO  establishmentResponseDTO = establishmentService.CreateEstablishment(postEstablishmentWithAddressDTO, user.getId());
    if (establishmentResponseDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(establishmentResponseDTO);
  }

  @DeleteMapping("/{establishmentId}")
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<EstablishmentResponseDTO> deleteEstablishment(
    @AuthenticationPrincipal User user,
    @PathVariable Long establishmentId
  ) {
    EstablishmentResponseDTO establishmentResponseDTO = establishmentService.deleteEstablishment(establishmentId);
    if (establishmentResponseDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(establishmentResponseDTO);
  }
}
