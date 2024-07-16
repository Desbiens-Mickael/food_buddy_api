package fr.olprog_b.food_buddy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.olprog_b.food_buddy.dto.product.PostProductDTO;
import fr.olprog_b.food_buddy.dto.product.ProductResponseDTO;
import fr.olprog_b.food_buddy.model.Product;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.service.AuthentificationService;
import fr.olprog_b.food_buddy.service.ProductService;

@RestController
@RequestMapping("/establishments/{establishmentId}/products")
public class ProductController {
  private final ProductService productService;
  private final AuthentificationService authentificationService;

  public ProductController(
    ProductService productService, 
    AuthentificationService authentificationService
    ) {
    this.productService = productService;
    this.authentificationService = authentificationService;
  }
  
  @GetMapping()
  public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@PathVariable Long establishmentId) {
      List<ProductResponseDTO> products = productService.findAllProduct(establishmentId);
      if (products.isEmpty()) {
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long establishmentId, @PathVariable Long id) {
      ProductResponseDTO Product = productService.findProductById(establishmentId, id);

      return ResponseEntity.ok(Product);
  }

  @PostMapping()
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<ProductResponseDTO> createProduct(@AuthenticationPrincipal User user, @PathVariable Long establishmentId, @RequestBody PostProductDTO productDTO) {
    if(!authentificationService.isAuthorEstablisment(user.getId(), establishmentId)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    ProductResponseDTO newProduct = productService.createProduct(productDTO, establishmentId);
      if(newProduct == null) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<ProductResponseDTO> updateProduct(@AuthenticationPrincipal User user,@PathVariable Long establishmentId, @PathVariable Long id, @RequestBody PostProductDTO productDTO) {
    if(!authentificationService.isAuthorEstablisment(user.getId(), establishmentId)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    ProductResponseDTO product = productService.updateProduct(establishmentId, id, productDTO);
      if (product == null) {
        return ResponseEntity.noContent().build();
      }

      return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_MERCHANT')")
  public ResponseEntity<Product> deleteProduct(@AuthenticationPrincipal User user, @PathVariable Long establishmentId, @PathVariable Long id) {
      if(!authentificationService.isAuthorEstablisment(user.getId(), establishmentId)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
      if (productService.deleteProduct(establishmentId, id)) {
        return ResponseEntity.noContent().build();
      } else {  
        return ResponseEntity.notFound().build();
      }
  }
  
}
