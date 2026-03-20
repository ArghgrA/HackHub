package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetSubmissionRequestDTO(
        @NotNull(message = "{GetSubmissionRequestDTO.hackathonId.NotNull}")
        UUID hackathonId
) {
}
