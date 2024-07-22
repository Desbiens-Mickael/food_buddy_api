package fr.olprog_b.food_buddy.dto.business.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.business.BusinessResponseDTO;
import fr.olprog_b.food_buddy.dto.establishment.mapper.EstablishmentResponseWithAddressMapper;
import fr.olprog_b.food_buddy.model.Business;

@Component
public class BusinessResponseMapper {
  public static BusinessResponseDTO convertToDto(Business business) {
    return new BusinessResponseDTO(
      business.getName(),
      business.getSiren(),
      business.getLogoUrl(),
      business.getEstablishments().stream().map(EstablishmentResponseWithAddressMapper::convertToDto).collect(Collectors.toList())
    );
  }
}
