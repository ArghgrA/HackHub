package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AcceptCallRequestDTO(
        @NotNull(message = "{AcceptTeamRequestDTO.callId.NotNull}")
        UUID callId
) {
}
