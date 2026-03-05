package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateHackathonRequestDTO(
        @NotEmpty @Size(min = 3,max = 30)
        String name,
        @NotEmpty @Size(max = 100)
        String rule,
        @Min(2)
        Integer maxTeamMembers,
        @NotNull
        UUID organizerId,
        @NotNull
        LocalDateTime registrationStart,
        @NotNull
        LocalDateTime registrationEnd,
        @NotNull
        LocalDateTime competitionStart,
        @NotNull
        LocalDateTime competitionEnd
) {
}
