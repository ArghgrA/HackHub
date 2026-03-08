package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record AddSubmissionRequestDTO(
        @NotNull(message = "{AddSubmissionRequestDTO.teamId.NotNull}")
        UUID teamId,
        @NotNull(message = "{AddSubmissionRequestDTO.hackathonId.NotNull}")
        UUID hackathonId,
        @NotNull(message = "{AddSubmissionRequestDTO.file.NotNull}")
        MultipartFile file
) {
}