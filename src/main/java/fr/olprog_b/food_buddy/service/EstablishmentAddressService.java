package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.establishmentAddress.AddressResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.EstablishmentAddressResponseDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.PostEstablishmentAddressDTO;
import fr.olprog_b.food_buddy.dto.establishmentAddress.mapper.AddressResponseMapper;
import fr.olprog_b.food_buddy.dto.establishmentAddress.mapper.EstablishmentAddressResponseMapper;
import fr.olprog_b.food_buddy.model.Establishment;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;
import fr.olprog_b.food_buddy.repository.EstablishmentAddressRepository;

@Service
public class EstablishmentAddressService {
  private final EstablishmentAddressRepository establishmentAddressRepository;

  public EstablishmentAddressService(EstablishmentAddressRepository establishmentAddressRepository) {
    this.establishmentAddressRepository = establishmentAddressRepository;
  }

  public List<EstablishmentAddressResponseDTO> getAllEstablishmentAddress(String keyword) {
    List<EstablishmentAddress> establishmentAddress;
    if (keyword == null || keyword.isEmpty()) {
      establishmentAddress = establishmentAddressRepository.findAll();
    } else {
      establishmentAddress = establishmentAddressRepository.findByEstablishmentNameContainingIgnoreCase(keyword);
    }
    if (establishmentAddress == null) {
      return null;
    }
    return establishmentAddress.stream().map(EstablishmentAddressResponseMapper::convertToDto).collect(Collectors.toList());
  }

  public AddressResponseDTO getAddress(Long id) {
    EstablishmentAddress establishmentaddress = establishmentAddressRepository.findById(id).orElse(null);
    if (establishmentaddress == null) {
      return null;
    }
    return AddressResponseMapper.convertToDto(establishmentaddress);
  }

  public AddressResponseDTO updateAddress(PostEstablishmentAddressDTO establishmentaddress, Long id) {
    EstablishmentAddress establishmentaddressToUpdate = establishmentAddressRepository.findById(id).orElse(null);
    if (establishmentaddressToUpdate == null) {
      return null;
    }
    establishmentaddressToUpdate.setStreetNumber(establishmentaddress.streetNumber());
    establishmentaddressToUpdate.setStreetName(establishmentaddress. streetName());
    establishmentaddressToUpdate.setZipCode(establishmentaddress.zipCode());
    establishmentaddressToUpdate.setCity(establishmentaddress.city());
    establishmentaddressToUpdate.setLatitude(establishmentaddress.latitude());
    establishmentaddressToUpdate.setLongitude(establishmentaddress.longitude());
    establishmentAddressRepository.save(establishmentaddressToUpdate);
    return AddressResponseMapper.convertToDto(establishmentaddressToUpdate);
  }

  public AddressResponseDTO CreateAddress(PostEstablishmentAddressDTO establishmentaddress, Establishment establishment) {
    EstablishmentAddress establishmentaddressToAdd = AddressResponseMapper.convertToEntity(establishmentaddress);
    establishmentaddressToAdd.setEstablishment(establishment);
    establishmentAddressRepository.save(establishmentaddressToAdd);
    return AddressResponseMapper.convertToDto(establishmentaddressToAdd);
  }
}
