package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReportTeamRequestDTO(
        @NotNull
        UUID teamId,
        @NotNull
        UUID mentorId,
        @NotNull
        UUID hackathonId,
        @NotEmpty
        String message
) {
}
