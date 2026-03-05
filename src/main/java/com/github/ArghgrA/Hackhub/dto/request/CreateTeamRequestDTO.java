package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateTeamRequestDTO(
        @NotNull
        UUID userId,
        @NotEmpty @Size(min = 3,max = 30)
        String name
) {
}
