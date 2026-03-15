package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetCallRequestDTO(
        @NotNull(message = "{GetCallRequestDTO.teamId.NotNull}")
        UUID teamId,
        @NotNull(message = "{GetCallRequestDTO.hackathonId.NotNull}")
        UUID hackathonId
) {
}
