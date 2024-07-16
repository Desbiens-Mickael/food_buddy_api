package fr.olprog_b.food_buddy.dto.reservation;

import java.time.LocalDateTime;

public record ReservationResponseDTO(
    LocalDateTime validUntil,
    LocalDateTime createdAt,
    String validationCode
) {}
