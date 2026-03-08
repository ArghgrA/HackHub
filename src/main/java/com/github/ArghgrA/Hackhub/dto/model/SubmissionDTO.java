package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record SubmissionDTO(
        UUID submissionId,
        UUID teamId,
        UUID hackathonId
) {
}
