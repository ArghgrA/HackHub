package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record AddSubmissionRequestDTO(
        @NotNull
        UUID teamId,
        @NotNull
        UUID hackathonId,
        @NotNull
        MultipartFile file
) {
}
