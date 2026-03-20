package com.github.ArghgrA.Hackhub.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddCallRequestDTO (
        @NotNull(message = "{AddCallRequestDTO.ticketId.NotNull}")
        UUID ticketId,
        @NotNull(message = "{AddCallRequestDTO.mentorId.NotNull}")
        UUID mentorId,
        @NotNull(message = "{AddCallRequestDTO.hackathonId.NotNull}")
        UUID hackathonId,
        @NotNull(message = "{AddCallRequestDTO.message.NotNull}")
        LocalDateTime message
){ }
