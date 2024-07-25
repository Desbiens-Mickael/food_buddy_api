package fr.olprog_b.food_buddy.service;

import java.util.Optional;


import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.business.BusinessResponseDTO;
import fr.olprog_b.food_buddy.dto.business.mapper.BusinessResponseMapper;
import fr.olprog_b.food_buddy.model.Business;
import fr.olprog_b.food_buddy.repository.BusinessRepository;

@Service
public class BusinessService {
  private final BusinessRepository businessRepository;

  public BusinessService(BusinessRepository businessRepository) {
    this.businessRepository = businessRepository;
  }

  public BusinessResponseDTO businessLogoPatch(Long businessId, String logoUrl) {
    Optional<Business> optionalBusiness = businessRepository.findById(businessId);
    if (!optionalBusiness.isPresent()) {
      return null;
    }
    Business business = optionalBusiness.get();
    business.setLogoUrl(logoUrl);
    Business updatedBusiness = businessRepository.save(business);

    return BusinessResponseMapper.convertToDto(updatedBusiness);
  }
}
