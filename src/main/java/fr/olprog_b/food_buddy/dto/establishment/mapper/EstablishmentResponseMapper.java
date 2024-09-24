package fr.olprog_b.food_buddy.dto.establishment.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.establishment.EstablishmentResponseDTO;
import fr.olprog_b.food_buddy.dto.establishment.PostEstablishmentDTO;
import fr.olprog_b.food_buddy.model.Establishment;

@Component
public class EstablishmentResponseMapper {
  public static Establishment convertToEntity(PostEstablishmentDTO postEstablishmentDTO) {
    Establishment establishment = new Establishment();
    establishment.setName(postEstablishmentDTO.name());
    establishment.setSiret(postEstablishmentDTO.siret());
    establishment.setEmail(postEstablishmentDTO.email());
    establishment.setPhoneNumber(postEstablishmentDTO.phoneNumber());
    return establishment;
  }

  public static EstablishmentResponseDTO convertToDto(Establishment establishment) {
    return new EstablishmentResponseDTO(
      establishment.getId(),
      establishment.getName(),
      establishment.getSiret(),
      establishment.getEmail(),
      establishment.getPhoneNumber()
    );
  }
}
