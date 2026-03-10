package com.github.ArghgrA.Hackhub.dto.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record HackathonDTO(
        UUID id,
        UUID organizerId,
        String name,
        String rule,
        Integer maxTeamMembers,
        BigDecimal price,
        String state,
        LocalDateTime registrationStart,
        LocalDateTime registrationEnd,
        LocalDateTime competitionStart,
        LocalDateTime competitionEnd
) {
}
