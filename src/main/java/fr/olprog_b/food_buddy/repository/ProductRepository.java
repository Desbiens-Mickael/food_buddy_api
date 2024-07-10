package fr.olprog_b.food_buddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
