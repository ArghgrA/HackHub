package com.github.ArghgrA.Hackhub.dto.response;

import java.util.UUID;

public record SubscribeTeamToHackathonResponseDTO(
        UUID idTeam,
        UUID idHackathon
) {
}
