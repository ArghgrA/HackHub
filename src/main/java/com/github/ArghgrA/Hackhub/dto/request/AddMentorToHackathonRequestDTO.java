package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AddMentorToHackathonRequestDTO(

        @NotNull(message = "{AddMentorToHackathonRequestDTO.mentorId.NotNull}")
        UUID mentorId,

        @NotNull(message = "{AddMentorToHackathonRequestDTO.organizerId.NotNull}")
        UUID organizerId
) {}