package fr.olprog_b.food_buddy.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "establishments")
public class Establishment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;

  @Column(unique = true, length = 14)
  private String siret;

  @Column()
  private String email;

  @Column()
  private String phoneNumber;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Column(updatable = true)
  private LocalDateTime updatedAt;

  @ManyToOne()
  @JoinColumn(name = "business_id", nullable = false)
  private Business business;

  @OneToOne(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
  private EstablishmentAddress address;
  
  @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products;

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSiret() {
    return siret;
  }

  public void setSiret(String siret) {
    this.siret = siret;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
  }

  public EstablishmentAddress getAddress() {
    return address;
  }

  public void setAddress(EstablishmentAddress address) {
    this.address = address;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
