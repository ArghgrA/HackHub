package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record InviteDTO(
        UUID id,
        UUID teamId,
        UUID userId,
        String message
) {
}
