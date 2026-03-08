package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReportTeamRequestDTO(
        @NotNull(message = "{ReportTeamRequestDTO.teamId.NotNull}")
        UUID teamId,
        @NotNull(message = "{ReportTeamRequestDTO.mentorId.NotNull}")
        UUID mentorId,
        @NotNull(message = "{ReportTeamRequestDTO.hackathonId.NotNull}")
        UUID hackathonId,
        @NotEmpty(message = "{ReportTeamRequestDTO.message.NotEmpty}")
        String message
) {
}