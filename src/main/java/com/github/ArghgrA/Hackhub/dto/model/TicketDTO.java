package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record TicketDTO(
        UUID ticketId,
        UUID teamId,
        String message
) {
}
