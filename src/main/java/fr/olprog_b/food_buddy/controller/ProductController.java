package fr.olprog_b.food_buddy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.model.Product;

@RestController
@RequestMapping("/establishments/{establishmentId}/products")
public class ProductController {
  
  @GetMapping()
  public ResponseEntity<List<Product>> getAllProducts(@PathVariable Long establishmentId) {
      // TODO: Modifier pour ajouter la récupération des produits
      List<Product> products = List.of();
      if (products.isEmpty()) {
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long establishmentId, @PathVariable Long id) {
      // TODO: Modifier pour ajouter la récupération du produit
      Optional<Product> optionalProduct = Optional.empty();
      if (!optionalProduct.isPresent()) {
        return ResponseEntity.noContent().build();
      }
      Product product = optionalProduct.orElse(null);

      return ResponseEntity.ok(product);
  }

  @PostMapping()
  public ResponseEntity<Product> createProduct(@PathVariable Long establishmentId, @RequestBody Product product) {
      // TODO: Modifier pour ajouter la création du produit
      Product newProduct = null;
      return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
  }

  @PutMapping()
  public ResponseEntity<Product> updateProduct(@PathVariable Long establishmentId, @PathVariable Long id) {
      // TODO: Modifier pour ajouter la modification du produit
      Optional<Product> optionalProduct = Optional.empty();
      if (!optionalProduct.isPresent()) {
        return ResponseEntity.noContent().build();
      }

      Product product = optionalProduct.orElse(null);
      return ResponseEntity.ok(product);
  }

  @DeleteMapping()
  public ResponseEntity<Product> deleteProduct(@PathVariable Long establishmentId, @PathVariable Long id) {
      // TODO: Modifier pour ajouter la suppression du produit
      Optional<Product> optionalProduct = Optional.empty();
      if (!optionalProduct.isPresent()) {
        return ResponseEntity.noContent().build();
      }
      Product product = null;
      return ResponseEntity.ok(product);
  }
  
}
