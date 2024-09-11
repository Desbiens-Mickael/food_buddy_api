package fr.olprog_b.food_buddy.dto.user.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.olprog_b.food_buddy.dto.business.mapper.PostBusinessMapper;
import fr.olprog_b.food_buddy.dto.establishment.mapper.PostEstablishmentMapper;
import fr.olprog_b.food_buddy.dto.establishmentAddress.mapper.PostEstablishmentaddressMapper;
import fr.olprog_b.food_buddy.dto.user.PostUserMerchantDTO;
import fr.olprog_b.food_buddy.enums.UserRole;
import fr.olprog_b.food_buddy.model.Business;
import fr.olprog_b.food_buddy.model.Establishment;
import fr.olprog_b.food_buddy.model.EstablishmentAddress;
import fr.olprog_b.food_buddy.model.User;

@Component
public class PostUserMerchantMapper {
  private final PostUserMapper postUserMapper;

  public PostUserMerchantMapper(PostUserMapper postUserMapper) {
    this.postUserMapper = postUserMapper;
  }
  public User convertToEntity(PostUserMerchantDTO newUser) {
    EstablishmentAddress establishmentAddress = PostEstablishmentaddressMapper.convertToEntity(newUser.address());

    Establishment establishment = PostEstablishmentMapper.convertToEntity(newUser.establishment());
    establishment.setAddress(establishmentAddress);
    establishmentAddress.setEstablishment(establishment);

    Business business = PostBusinessMapper.convertToEntity(newUser.business());
    business.setEstablishments(List.of(establishment));
    establishment.setBusiness(business);

    User user = postUserMapper.convertToEntity(newUser.newUser());
    user.setRole(UserRole.MERCHANT);
    // TODO : Fait
    user.setBusiness(business);
    business.setUser(user);

    return user;
  }
}
