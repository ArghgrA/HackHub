package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetReportRequestDTO(
        @NotNull(message = "{GetReportRequestDTO.hackathonId.NotNull}")
        UUID hackathonId
) {
}
