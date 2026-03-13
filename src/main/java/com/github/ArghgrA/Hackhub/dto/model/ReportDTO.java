package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record ReportDTO(
        UUID id,
        UUID mentorId,
        UUID teamId,
        UUID hackathonId,
        String message
) {
}
