package fr.olprog_b.food_buddy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.establishmentAddress.AddressResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.PostEstablishmentAddressDTO;
import fr.olprog_b.food_buddy.service.EstablishmentAddressService;

@RestController
@RequestMapping("/establishmentAddresses")
public class EstablishmentAddressController {
  private final EstablishmentAddressService establishmentAddressService;

  public EstablishmentAddressController(EstablishmentAddressService establishmentAddressService) {
    this.establishmentAddressService = establishmentAddressService;
  }

  @GetMapping
  public ResponseEntity<List<EstablishmentAddressResponseDTO>> getAllEstablishmentAddress(@RequestParam String keyword) {
    List<EstablishmentAddressResponseDTO> establishmentAddresseDTOs = establishmentAddressService.getAllEstablishmentAddress(keyword);
    if (establishmentAddresseDTOs.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(establishmentAddresseDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressResponseDTO> getAddress(@PathVariable Long id) {
    AddressResponseDTO addressResponseDTO = establishmentAddressService.getAddress(id);
    if (addressResponseDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(addressResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id, @RequestBody PostEstablishmentAddressDTO address) {
    AddressResponseDTO addressResponseDTO = establishmentAddressService.updateAddress(address, id);
    if (addressResponseDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(addressResponseDTO);
  }
}
