package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetInviteRequestDTO(
        @NotNull(message = "{GetInviteRequestDTO.userId.NotNull}")
        UUID userId
) {
}
