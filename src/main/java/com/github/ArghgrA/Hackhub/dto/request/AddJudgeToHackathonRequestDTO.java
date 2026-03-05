package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddJudgeToHackathonRequestDTO(
        @NotNull
        UUID judgeId,
        @NotNull
        UUID organizerId
) {}
