package fr.olprog_b.food_buddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.olprog_b.food_buddy.enums.ProductStatus;
import fr.olprog_b.food_buddy.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByEstablishmentId(Long establishmentId);

  List<Product> findByEstablishmentIdAndNumberAvailableGreaterThan(Long establishmentId, int zero);

  List<Product> findByEstablishmentIdAndStatusEquals(Long establishmentId, ProductStatus  status);
}