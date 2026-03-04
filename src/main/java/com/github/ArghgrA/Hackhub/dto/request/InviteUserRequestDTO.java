package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.util.UUID;

public record InviteUserRequestDTO(
        @NotNull
        UUID userId,
        @NotNull
        UUID teamId,
        @NotEmpty @Size(max = 200)
        String message
) {
}
