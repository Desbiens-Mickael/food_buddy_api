package fr.olprog_b.food_buddy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.product.PostProductDTO;
import fr.olprog_b.food_buddy.dto.product.ProductResponseDTO;
import fr.olprog_b.food_buddy.dto.product.mapper.PostProductMapper;
import fr.olprog_b.food_buddy.dto.product.mapper.ProductResponseMapper;
import fr.olprog_b.food_buddy.model.Establishment;
import fr.olprog_b.food_buddy.model.Product;
import fr.olprog_b.food_buddy.repository.AllergenRepository;
import fr.olprog_b.food_buddy.repository.EstablishmentRepository;
import fr.olprog_b.food_buddy.repository.ProductRepository;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final EstablishmentRepository establishmentRepository;
  private final AllergenRepository allergenRepository;

  public ProductService(
    ProductRepository productRepository, 
    EstablishmentRepository establishmentRepository, 
    AllergenRepository allergenRepository
    ) {
    this.productRepository = productRepository;
    this.establishmentRepository = establishmentRepository;
    this.allergenRepository = allergenRepository;
  }

  // Création d'un produit
  public ProductResponseDTO createProduct(PostProductDTO postProductDTO, Long establishmentId) {
    Establishment establishment = establishmentRepository.findById(establishmentId).orElseThrow();
    // List<Allergen> allergens = allergenRepository.findAllById(postProductDTO.allergensIds());
    Product product = PostProductMapper.convertToEntity(postProductDTO);
    product.setEstablishment(establishment);
    product.setAllergens(allergenRepository.findAllById(postProductDTO.allergensIds()));
    return ProductResponseMapper.convertToDTO(productRepository.save(product));
  }

  // Récupération de tous les produits d'un établissement
  public List<ProductResponseDTO> findAllProduct(Long establishmentId) {
    Optional<Establishment> optionalEstablishment = establishmentRepository.findById(establishmentId);
    if (!optionalEstablishment.isPresent()) {
      throw new IllegalArgumentException("Establishment not found");
    }
    
    List<Product> products = productRepository.findAllByEstablishmentId(optionalEstablishment.get().getId());
    return products.stream().map(ProductResponseMapper::convertToDTO).collect(Collectors.toList());
  }

  // Récupération d'un produit par son id
  public ProductResponseDTO findProductById(Long establishmentId, Long id) {
    Optional<Establishment> optionalEstablishment = establishmentRepository.findById(establishmentId);
    if (!optionalEstablishment.isPresent()) {
      throw new IllegalArgumentException("Establishment not found");
    }

    Optional<Product> optionalProduct = productRepository.findById(id);
    if (!optionalProduct.isPresent()) {
      throw new IllegalArgumentException("Product not found");
    }
    return ProductResponseMapper.convertToDTO(optionalProduct.get());
  }

  // Mise à jour d'un produit
  public ProductResponseDTO updateProduct(Long establishmentId, Long id, PostProductDTO postProductDTO) {
    Optional<Establishment> optionalEstablishment = establishmentRepository.findById(establishmentId);
    if (!optionalEstablishment.isPresent()) {
      throw new IllegalArgumentException("Establishment not found");
    }

    Optional<Product> optionalProduct = productRepository.findById(id);
    if (!optionalProduct.isPresent()) {
      throw new IllegalArgumentException("Product not found");
    }

    Product product = PostProductMapper.convertToEntity(postProductDTO);
    product.setId(id);
    product.setEstablishment(optionalEstablishment.get());
    product.setAllergens(allergenRepository.findAllById(postProductDTO.allergensIds()));
    return ProductResponseMapper.convertToDTO(productRepository.save(product));
  }

  // Suppression d'un produit
  public Boolean deleteProduct(Long establishmentId, Long id) {
    Optional<Establishment> optionalEstablishment = establishmentRepository.findById(establishmentId);
    if (!optionalEstablishment.isPresent()) {
      throw new IllegalArgumentException("Establishment not found");
    }

    Optional<Product> optionalProduct = productRepository.findById(id);
    if (!optionalProduct.isPresent()) {
      throw new IllegalArgumentException("Product not found");
    }

    productRepository.delete(optionalProduct.get());
    return true;
  }
}
