package fr.olprog_b.food_buddy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.olprog_b.food_buddy.dto.reservation.ReservationResponseDTO;
import fr.olprog_b.food_buddy.dto.reservation.mapper.ReservationResponseMapper;
import fr.olprog_b.food_buddy.enums.ProductStatus;
import fr.olprog_b.food_buddy.enums.ProductType;
import fr.olprog_b.food_buddy.model.DailyConsumption;
import fr.olprog_b.food_buddy.model.Product;
import fr.olprog_b.food_buddy.model.Reservation;
import fr.olprog_b.food_buddy.model.User;
import fr.olprog_b.food_buddy.repository.DailyConsumptionRepository;
import fr.olprog_b.food_buddy.repository.ProductRepository;
import fr.olprog_b.food_buddy.repository.ReservationRepository;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.transaction.Transactional;

@Service
public class ReservationService {
  private final ReservationRepository reservationRepository;
  private final ProductRepository productRepository;
  private final DailyConsumptionRepository dailyConsumptionRepository;

  public ReservationService(
    ReservationRepository reservationRepository, 
    ProductRepository productRepository,
    DailyConsumptionRepository dailyConsumptionRepository
    ) {
    this.reservationRepository = reservationRepository;
    this.productRepository = productRepository;
    this.dailyConsumptionRepository = dailyConsumptionRepository;
  }

    // Vérifie si la réservation est valide
  private Boolean isReservationValid(Reservation reservation) {
    return reservation.getValidUntil().isAfter(LocalDateTime.now());
  }

  // vérifie si le code de validation est valide
  private Boolean isValidationCodeValid(Reservation reservation, String validationCode) {
    Optional<Reservation> optionalReservation = reservationRepository.findById(reservation.getId());
    if (optionalReservation.isEmpty()) {
      return false;
    }
    if (!reservation.getValidationCode().equals(validationCode)) {
      return false;
    }
    return true;
  }

  @Transactional
  public ReservationResponseDTO createReservation(User user, Long productId) {
    // Récupérer le produit
    Optional<Product> optionalProduct = productRepository.findById(productId);

    // Si le produit n'existe pas
    if (optionalProduct.isEmpty()) {
      throw new IllegalArgumentException("Product not found");
    }

    // Si le produit est indisponible
    if (optionalProduct.get().getStatus() == ProductStatus.UNAVAILABLE || optionalProduct.get().getNumberAvailable() == 0) {
      throw new IllegalArgumentException("Product unavailable");
    }
    
    // Vérifier si le produit a déjà été consommé aujourd'hui
    ProductType productType = optionalProduct.get().getType();
    LocalDate today = LocalDate.now();
    DailyConsumption dailyConsumption = dailyConsumptionRepository.findByUserAndConsumptionTypeAndToday(user, productType, today);
    
    // Si le produit est déjà consommé aujourd'hui
    if (dailyConsumption != null) {
      throw new IllegalArgumentException("Product already consumed today");
    }

    // Créer une nouvelle réservation
    Reservation reservation = new Reservation();
    reservation.setUser(user);
    reservation.setProduct(optionalProduct.get());
    Reservation createdReservation = reservationRepository.save(reservation);

    // Si la réservation a été créée
    if (createdReservation != null) {
    // Créer une nouvelle consommation journalière
      DailyConsumption newDailyConsumption = new DailyConsumption();
      newDailyConsumption.setConsumptionType(optionalProduct.get().getType());
      newDailyConsumption.setProduct(optionalProduct.get());
      newDailyConsumption.setUser(user);
      dailyConsumptionRepository.save(newDailyConsumption);
    }

    optionalProduct.get().setNumberAvailable(optionalProduct.get().getNumberAvailable() - 1);
    productRepository.save(optionalProduct.get());

    return ReservationResponseMapper.convertToDto(createdReservation);
  }

  // Recherche une réservation par code de validation
  public ReservationResponseDTO getByValidationCode(String validationCode) {
    Optional<Reservation> optionalReservation = reservationRepository.findByValidationCode(validationCode);
    if (optionalReservation.isEmpty()) {
      return null;
    }
    return ReservationResponseMapper.convertToDto(optionalReservation.get());
  }

  // Retourne la liste des réservations de l'utilisateur
  public List<ReservationResponseDTO> findByUser(User user) {
    Optional<List<Reservation>> optionalReservations = reservationRepository.findByUser(user);
    if (optionalReservations.isEmpty()) {
      return null;
    }
    return optionalReservations.get().stream().map(ReservationResponseMapper::convertToDto).collect(Collectors.toList());
  }

  // Retourne le nombre de réservations pour un produit
  public Integer getNumberOfReservationsByProduct(Long productId) {
    Optional<Product> optionalProduct = productRepository.findById(productId);
    if (optionalProduct.isEmpty()) {
      return null;
    }
    return optionalProduct.get().getReservations().size();
  }

  // Retourne la liste des réservations du produit
  public List<ReservationResponseDTO> getByProduct(Long productId) {
    Optional<Product> optionalProduct = productRepository.findById(productId);
    if (optionalProduct.isEmpty()) {
      return null;
    }

    List<Reservation> reservations = optionalProduct.get().getReservations();
    return reservations.stream().map(ReservationResponseMapper::convertToDto).collect(Collectors.toList());
  }

  // Supprime la réservation
  public Boolean deleteReservation(Long reservationId, String validationCode) {
    Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
    if (optionalReservation.isEmpty()) {
      return false;
    }
    Reservation reservation = optionalReservation.get();
    if (!isReservationValid(reservation) || !isValidationCodeValid(reservation, validationCode)) {
      return false;
    }
    reservationRepository.delete(reservation);
    return true;
  }
}
