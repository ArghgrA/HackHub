package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateHackathonRequestDTO(

        @NotEmpty(message = "{CreateHackathonRequestDTO.name.NotEmpty}")
        @Size(min = 3, max = 30, message = "{CreateHackathonRequestDTO.name.Size}")
        String name,

        @NotEmpty(message = "{CreateHackathonRequestDTO.rule.NotEmpty}")
        @Size(max = 100, message = "{CreateHackathonRequestDTO.rule.Size}")
        String rule,

        @Min(value = 2, message = "{CreateHackathonRequestDTO.maxTeamMembers.min}")
        Integer maxTeamMembers,

        @NotNull(message = "{CreateHackathonRequestDTO.organizerId.NotNull}")
        UUID organizerId,

        @NotNull(message = "{CreateHackathonRequestDTO.registrationStart.NotNull}")
        @Future(message = "{CreateHackathonRequestDTO.registrationStart.Future}")
        LocalDateTime registrationStart,

        @NotNull(message = "{CreateHackathonRequestDTO.registrationEnd.NotNull}")
        @Future(message = "{CreateHackathonRequestDTO.registrationEnd.Future}")
        LocalDateTime registrationEnd,

        @NotNull(message = "{CreateHackathonRequestDTO.competitionStart.NotNull}")
        @Future(message = "{CreateHackathonRequestDTO.competitionStart.Future}")
        LocalDateTime competitionStart,

        @NotNull(message = "{CreateHackathonRequestDTO.competitionEnd.NotNull}")
        @Future(message = "{CreateHackathonRequestDTO.competitionEnd.Future}")
        LocalDateTime competitionEnd,

        @Min(value = 0, message = "{CreateHackathonRequestDTO.price.min}")
        BigDecimal price
) {}