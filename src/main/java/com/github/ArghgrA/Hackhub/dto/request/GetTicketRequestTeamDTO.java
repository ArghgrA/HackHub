package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetTicketRequestTeamDTO(
        @NotNull(message = "{GetTicketRequestTeamDTO.teamMemberId.NotNull}")
        UUID teamMemberId,
        @NotNull(message = "{GetTicketRequestTeamDTO.teamId.NotNull}")
        UUID teamId
) {
}
