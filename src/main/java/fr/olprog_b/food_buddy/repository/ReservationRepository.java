package fr.olprog_b.food_buddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  
}
