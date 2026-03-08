package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetTicketRequestDTO(
        @NotNull(message = "{GetTicketRequestDTO.hackathonId.NotNull}")
        UUID hackathonId
) {
}