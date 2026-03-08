package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record ReportDTO(
        UUID reportId,
        UUID mentorId,
        UUID teamId,
        String message
) {
}
