package com.github.ArghgrA.Hackhub.dto.DTOCreazione;

import java.util.UUID;

public record addJudgeDTO(
        UUID judgeId,
        UUID hackathonId
){}
