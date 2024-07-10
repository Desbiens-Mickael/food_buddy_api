package fr.olprog_b.food_buddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.model.EstablishmentAddress;

public interface EstablishmentAddressRepository extends JpaRepository<EstablishmentAddress, Long> {
  
}
