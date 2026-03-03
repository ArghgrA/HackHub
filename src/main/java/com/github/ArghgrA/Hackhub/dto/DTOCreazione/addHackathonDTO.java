package com.github.ArghgrA.Hackhub.dto.DTOCreazione;

import java.time.LocalDateTime;

public record addHackathonDTO(
        String name,
        String rule,
        Integer maxTeamMembers,
        LocalDateTime inizioIscrizione,
        LocalDateTime fineIscrizione,
        LocalDateTime inizioCompetizione,
        LocalDateTime fineCompetizione
){}
