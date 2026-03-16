package com.github.ArghgrA.Hackhub.dto.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record CallDTO(
        UUID id,
        LocalDateTime date,
        UUID ticketId,
        UUID mentorId,
        UUID teamId
) {
}