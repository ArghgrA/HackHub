package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetEvaluationRequestDTO(
        @NotNull(message = "{GetEvaluationRequestDTO.hackathonId.NotNull}")
        UUID hackathonId
) {
}
