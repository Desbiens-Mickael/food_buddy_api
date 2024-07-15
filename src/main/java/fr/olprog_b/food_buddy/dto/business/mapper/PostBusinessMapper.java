package fr.olprog_b.food_buddy.dto.business.mapper;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.business.PostBusinessDTO;
import fr.olprog_b.food_buddy.model.Business;

@Component
public class PostBusinessMapper {
  public static Business convertToEntity(PostBusinessDTO postBusinessDTO) {
    Business business = new Business();
    business.setName(postBusinessDTO.name());
    business.setSiren(postBusinessDTO.siren());
    business.setLogoUrl(postBusinessDTO.logoUrl());
    return business;
  }
}
