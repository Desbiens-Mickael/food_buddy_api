package fr.olprog_b.food_buddy.service;

import java.util.Optional;


import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.business.BusinessResponseDTO;
import fr.olprog_b.food_buddy.dto.business.PostBusinessDTO;
import fr.olprog_b.food_buddy.dto.business.mapper.BusinessResponseMapper;
import fr.olprog_b.food_buddy.model.Business;
import fr.olprog_b.food_buddy.repository.BusinessRepository;

@Service
public class BusinessService {
  private final BusinessRepository businessRepository;

  public BusinessService(BusinessRepository businessRepository) {
    this.businessRepository = businessRepository;
  }

  public BusinessResponseDTO businessLogoPatch(Long userId, String logoUrl) {
    Optional<Business> optionalBusiness = businessRepository.findByUserId(userId);
    if (!optionalBusiness.isPresent()) {
      return null;
    }
    Business business = optionalBusiness.get();
    business.setLogoUrl(logoUrl);
    Business updatedBusiness = businessRepository.save(business);

    return BusinessResponseMapper.convertToDto(updatedBusiness);
  }

  public Optional<BusinessResponseDTO> getBusiness(Long userId) {
    Optional<Business> optionalBusiness = businessRepository.findByUserId(userId);
    if (!optionalBusiness.isPresent()) {
      return Optional.empty();
    }
    Business business = optionalBusiness.get();
    return Optional.of(BusinessResponseMapper.convertToDto(business));
  }

  public Optional<BusinessResponseDTO> updateBusiness(Long userId, PostBusinessDTO postBusinessDTO) {
    Optional<Business> optionalBusiness = businessRepository.findByUserId(userId);
    if (!optionalBusiness.isPresent()) {
      return Optional.empty();
    }
    Business business = optionalBusiness.get();
    business.setName(postBusinessDTO.name());
    Business updatedBusiness = businessRepository.save(business);

    return Optional.of(BusinessResponseMapper.convertToDto(updatedBusiness));
  }
}
