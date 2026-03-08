package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubscribeTeamRequestDTO(
        @NotNull(message = "{SubscribeTeamRequestDTO.idTeam.NotNull}")
        UUID idTeam,

        @NotNull(message = "{SubscribeTeamRequestDTO.idHackathon.NotNull}")
        UUID idHackathon
) {
}