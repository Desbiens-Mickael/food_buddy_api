package fr.olprog_b.food_buddy.dto.authentification;

import java.util.Optional;

import fr.olprog_b.food_buddy.enums.UserRole;

public class LoginResponseDTO {
  private String email;
  private String firstname;
  private String lastname;
  private String profileImageUrl;
  private UserRole role = UserRole.USER;
  private Boolean isEligible = false;
  private Optional<String> BusinesseName = Optional.empty();
  private Optional<String> BusinessLogoUrl = Optional.empty();

  public Optional<String> getBusinesseName() {
    return BusinesseName;
  }

  public void setBusinesseName(Optional<String> businesseName) {
    BusinesseName = businesseName;
  }

  public Optional<String> getBusinessLogoUrl() {
    return BusinessLogoUrl;
  }

  public void setBusinessLogoUrl(Optional<String> businessLogoUrl) {
    BusinessLogoUrl = businessLogoUrl;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public Boolean getIsEligible() {
    return isEligible;
  }

  public void setIsEligible(Boolean isEligible) {
    this.isEligible = isEligible;
  }

}
