package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record StaffDTO(
        UUID id,
        UUID hackathonId,
        String email,
        String role
) {
}
