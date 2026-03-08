package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddTicketRequestDTO(
        @NotNull(message = "{AddTicketRequestDTO.teamId.NotNull}")
        UUID teamId,

        @NotNull(message = "{AddTicketRequestDTO.hackathonId.NotNull}")
        UUID hackathonId,

        @NotEmpty(message = "{AddTicketRequestDTO.message.NotEmpty}")
        String message
) {
}