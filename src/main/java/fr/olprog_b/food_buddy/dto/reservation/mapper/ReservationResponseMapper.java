package fr.olprog_b.food_buddy.dto.reservation.mapper;

import fr.olprog_b.food_buddy.dto.reservation.ReservationResponseDTO;
import fr.olprog_b.food_buddy.model.Reservation;

public class ReservationResponseMapper {
  
  public static ReservationResponseDTO convertToDto(Reservation reservation) {
    return new ReservationResponseDTO(
      reservation.getId(),
      reservation.getProduct().getEstablishment().getName(),
      reservation.getProduct().getName(),
      reservation.getValidUntil(),
      reservation.getCreatedAt(),
      reservation.getValidationCode()
      );
  }
}
