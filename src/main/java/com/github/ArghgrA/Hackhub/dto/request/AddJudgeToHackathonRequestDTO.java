package com.github.ArghgrA.Hackhub.dto.request;

import java.util.UUID;

public record AddJudgeToHackathonRequestDTO(
        UUID idJudge,
        UUID idHackathon
) {}
