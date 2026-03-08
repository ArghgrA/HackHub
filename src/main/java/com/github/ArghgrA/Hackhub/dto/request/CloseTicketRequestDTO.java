package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CloseTicketRequestDTO(
        @NotNull
        UUID ticketId,

        @NotNull
        UUID mentorId
) {
}
