package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record CreateTeamRequestDTO(

        @NotNull(message = "{CreateTeamRequestDTO.userId.NotNull}")
        UUID userId,

        @NotEmpty(message = "{CreateTeamRequestDTO.name.NotEmpty}")
        @Size(min = 3, max = 30, message = "{CreateTeamRequestDTO.name.Size}")
        String name
) {}