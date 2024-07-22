package fr.olprog_b.food_buddy.dto.reservation;

import java.time.LocalDateTime;

public record ReservationResponseDTO(
    Long id,
    String establishmentname,
    String productname,
    LocalDateTime validUntil,
    LocalDateTime createdAt,
    String validationCode
) {}
