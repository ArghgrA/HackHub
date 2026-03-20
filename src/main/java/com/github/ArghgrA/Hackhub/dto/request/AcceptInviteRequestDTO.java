package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

    public record AcceptInviteRequestDTO(
        @NotNull(message = "{AcceptInviteRequestDTO.inviteId.NotNull}")
        UUID inviteId,
        @NotNull(message = "{AcceptInviteRequestDTO.userId.NotNull}")
        UUID userId
) {
}
