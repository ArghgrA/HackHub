package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddMentorToHackathonRequestDTO(
        @NotNull
        UUID mentorId,
        @NotNull
        UUID organizerId
) {
}
