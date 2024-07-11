package fr.olprog_b.food_buddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
