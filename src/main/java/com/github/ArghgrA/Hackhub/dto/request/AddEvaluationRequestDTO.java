package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AddEvaluationRequestDTO(
        @NotNull
        UUID judgeId,
        @NotNull
        UUID submissionId,
        @NotNull
        UUID hackathonId,
        @NotNull @Size(max = 10)
        Integer score,
        @NotEmpty
        String details
) {
}
