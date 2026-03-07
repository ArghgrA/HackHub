package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AddJudgeToHackathonRequestDTO(

        @NotNull(message = "{AddJudgeToHackathonRequestDTO.judgeId.NotNull}")
        UUID judgeId,

        @NotNull(message = "{AddJudgeToHackathonRequestDTO.organizerId.NotNull}")
        UUID organizerId
) {}