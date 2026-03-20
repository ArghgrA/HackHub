package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LeaveTeamRequestDTO(
        @NotNull(message = "{LeaveTeamRequestDTO.teamMemberId.NotNull}")
        UUID teamMemberId,
        @NotNull(message = "{LeaveTeamRequestDTO.teamId.NotNull}")
        UUID teamId
) {
}
