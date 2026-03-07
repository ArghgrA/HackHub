package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record InviteUserRequestDTO(

        @NotNull(message = "{InviteUserRequestDTO.userId.NotNull}")
        UUID userId,

        @NotNull(message = "{InviteUserRequestDTO.teamId.NotNull}")
        UUID teamId,

        @NotEmpty(message = "{InviteUserRequestDTO.message.NotEmpty}")
        @Size(max = 200, message = "{InviteUserRequestDTO.message.Size}")
        String message
) {}