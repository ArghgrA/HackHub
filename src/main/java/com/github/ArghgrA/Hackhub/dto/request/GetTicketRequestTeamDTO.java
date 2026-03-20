package com.github.ArghgrA.Hackhub.dto.request;

import java.util.UUID;

public record GetTicketRequestTeamDTO(
        UUID teamMemberId,
        UUID teamId
) {
}
