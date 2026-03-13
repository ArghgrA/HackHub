package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record EvaluationDTO(
        UUID id,
        UUID submissionId,
        UUID judgeId,
        Integer score,
        String details
) {
}
