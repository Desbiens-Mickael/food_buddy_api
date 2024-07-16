package fr.olprog_b.food_buddy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;
import fr.olprog_b.food_buddy.service.EstablishmentAddressService;

@RestController
@RequestMapping("/establishmentAddresses")
public class EstablishmentAddressController {
  private final EstablishmentAddressService establishmentAddressService;

  public EstablishmentAddressController(EstablishmentAddressService establishmentAddressService) {
    this.establishmentAddressService = establishmentAddressService;
  }

  @GetMapping
  public ResponseEntity<List<EstablishmentAddressResponseDTO>> getAllEstablishmentAddress() {
    List<EstablishmentAddressResponseDTO> establishmentAddresses = establishmentAddressService.getAllEstablishmentAddress();
    if (establishmentAddresses.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(establishmentAddresses);
  }
}
