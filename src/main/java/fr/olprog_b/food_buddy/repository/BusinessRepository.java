package fr.olprog_b.food_buddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.model.Business;

public interface BusinessRepository extends JpaRepository<Business, Long> {
  
}
