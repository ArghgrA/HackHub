package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.UUID;

public record SubscribeTeamToHackathonRequestDTO (
        @NotNull
        UUID idTeam,
        @NotNull
        UUID idHackathon
) {
}
