package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AddEvaluationRequestDTO(
        @NotNull(message = "{AddEvaluationRequestDTO.judgeId.NotNull}")
        UUID judgeId,
        @NotNull(message = "{AddEvaluationRequestDTO.submissionId.NotNull}")
        UUID submissionId,
        @NotNull(message = "{AddEvaluationRequestDTO.hackathonId.NotNull}")
        UUID hackathonId,
        @NotNull(message = "{AddEvaluationRequestDTO.score.NotNull}")
        @Size(max = 10, message = "{AddEvaluationRequestDTO.score.Size}")
        Integer score,
        @NotEmpty(message = "{AddEvaluationRequestDTO.details.NotEmpty}")
        String details
) {
}