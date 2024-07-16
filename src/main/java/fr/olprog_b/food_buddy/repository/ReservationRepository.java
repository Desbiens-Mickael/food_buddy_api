package fr.olprog_b.food_buddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.model.Product;
import fr.olprog_b.food_buddy.model.Reservation;
import fr.olprog_b.food_buddy.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<List<Reservation>> findByUser(User user);

  Optional<List<Reservation>> findByProduct(Product product);

  Optional<Reservation> findByValidationCode(String validationCode);
}
