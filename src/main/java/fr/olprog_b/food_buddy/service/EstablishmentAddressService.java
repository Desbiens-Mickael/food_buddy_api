package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.mapper.EstablishmentAddressResponseMapper;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;
import fr.olprog_b.food_buddy.repository.EstablishmentAddressRepository;

@Service
public class EstablishmentAddressService {
  private final EstablishmentAddressRepository establishmentAddressRepository;

  public EstablishmentAddressService(EstablishmentAddressRepository establishmentAddressRepository) {
    this.establishmentAddressRepository = establishmentAddressRepository;
  }

  public List<EstablishmentAddressResponseDTO> getAllEstablishmentAddress() {
    List<EstablishmentAddress> establishmentAddress = establishmentAddressRepository.findAll();
    if (establishmentAddress == null) {
      return null;
    }
    return establishmentAddress.stream().map(EstablishmentAddressResponseMapper::convertToDto).collect(Collectors.toList());
  }
}
