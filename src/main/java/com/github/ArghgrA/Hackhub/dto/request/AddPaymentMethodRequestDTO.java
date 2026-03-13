package com.github.ArghgrA.Hackhub.dto.request;

import com.github.ArghgrA.Hackhub.model.other.payment.PaymentKind;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddPaymentMethodRequestDTO(
        @NotNull(message = "{AddPaymentMethodRequestDTO.teamId.NotNull}")
        UUID teamId,
        @NotNull(message = "{AddPaymentMethodRequestDTO.kind.NotNull}")
        PaymentKind kind,
        @NotEmpty(message = "{AddPaymentMethodRequestDTO.address.NotEmpty}")
        String address
) {
}
