package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record TicketDTO(
        UUID id,
        UUID teamId,
        UUID hackathonId,
        String message
) {
}
