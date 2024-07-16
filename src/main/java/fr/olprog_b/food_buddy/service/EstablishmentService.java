package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;
import fr.olprog_b.food_buddy.dto.establishment.mapper.EstablishmentResponseMapper;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.repository.EstablishmentRepository;
import fr.olprog_b.food_buddy.repository.UserRepository;

@Service
public class EstablishmentService {
  private final EstablishmentRepository establishmentRepository;
  private final UserRepository userRepository;
  
  public EstablishmentService(
    EstablishmentRepository establishmentRepository,
    UserRepository userRepository
    ) {
    this.establishmentRepository = establishmentRepository;
    this.userRepository = userRepository;
  }

  public List<EstablishmentResponseDTO> getAllEstablishment(Long UserId) {
    User user = userRepository.findById(UserId).get();
    return establishmentRepository
            .findByBusinessId(user.getBusinesses()
            .get(0).getId())
            .stream()
            .map(EstablishmentResponseMapper::convertToDto)
            .collect(Collectors.toList());
  }
}
