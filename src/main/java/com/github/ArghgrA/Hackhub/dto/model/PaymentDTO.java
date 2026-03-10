package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record PaymentDTO(
        UUID id,
        UUID teamId
) {
}
