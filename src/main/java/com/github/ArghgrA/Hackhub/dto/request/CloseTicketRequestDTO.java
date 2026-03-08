package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CloseTicketRequestDTO(
        @NotNull(message = "{CloseTicketRequestDTO.ticketId.NotNull}")
        UUID ticketId,
        @NotNull(message = "{CloseTicketRequestDTO.mentorId.NotNull}")
        UUID mentorId
) {
}