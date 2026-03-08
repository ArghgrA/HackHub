package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record HackathonDTO(
        UUID hackathonId,
        String name,
        String rule,
        Integer maxTeamMembers
) {
}
