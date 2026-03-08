package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddTicketRequestDTO(
        @NotNull
        UUID teamId,
        @NotNull
        UUID hackathonId,
        @NotNull
        String message
) {
}
