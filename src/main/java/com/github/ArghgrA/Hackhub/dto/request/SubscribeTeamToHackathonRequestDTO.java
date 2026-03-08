package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubscribeTeamRequestDTO(
        @NotNull
        UUID idTeam,
        @NotNull
        UUID idHackathon
) {
}
