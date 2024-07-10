package fr.olprog_b.food_buddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
