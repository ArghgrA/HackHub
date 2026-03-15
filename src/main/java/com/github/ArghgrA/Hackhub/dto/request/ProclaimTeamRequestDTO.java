package com.github.ArghgrA.Hackhub.dto.request;

import com.github.ArghgrA.Hackhub.model.other.payment.PaymentKind;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProclaimTeamRequestDTO(
        @NotNull(message = "{ProclaimTeamRequestDTO.hackathonId.NotNull}")
        UUID hackathonId,
        @NotNull(message = "{ProclaimTeamRequestDTO.teamId.NotNull}")
        UUID teamId,
        @NotNull(message = "{ProclaimTeamRequestDTO.kind.NotNull}")
        PaymentKind kind
) {
}