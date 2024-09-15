package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;
import fr.olprog_b.food_buddy.dto.establishment.PostEstablishmentDTO;
import fr.olprog_b.food_buddy.dto.establishment.mapper.EstablishmentResponseMapper;
import fr.olprog_b.food_buddy.model.Establishment;
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
            .findByBusinessId(user.getBusiness().getId())
            .stream()
            .map(EstablishmentResponseMapper::convertToDto)
            .collect(Collectors.toList());
  }

  public EstablishmentResponseDTO updateEstablishment(PostEstablishmentDTO establishment, Long EstablishmentId) {
    Establishment establishmentToUpdate = establishmentRepository.findById(EstablishmentId).get();
    establishmentToUpdate.setName(establishment.name());
    establishmentToUpdate.setEmail(establishment.email());
    establishmentToUpdate.setPhoneNumber(establishment.phoneNumber());
    establishmentRepository.save(establishmentToUpdate);
    
    return EstablishmentResponseMapper.convertToDto(establishmentToUpdate);
  }
}
