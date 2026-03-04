package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateTeamRequestDTO(
        @NotEmpty @Size(min = 3,max = 30)
        String name
) {
}
