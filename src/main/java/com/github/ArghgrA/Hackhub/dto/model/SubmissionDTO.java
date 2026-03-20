package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record SubmissionDTO(
        UUID id,
        UUID teamId,
        UUID hackathonId,
        Byte[] message
) {
}
