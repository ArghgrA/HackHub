package com.github.ArghgrA.Hackhub.dto.request;

import com.github.ArghgrA.Hackhub.model.other.payment.PaymentKind;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProclaimTeamRequestDTO(
        @NotNull
        UUID hackathonId,
        @NotNull
        UUID teamId,
        @NotNull
        PaymentKind kind
) {
}
