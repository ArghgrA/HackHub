package com.github.ArghgrA.Hackhub.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddCallRequestDTO (
        UUID ticketId,
        UUID mentorId,
        UUID hackathonId,
        LocalDateTime message
){ }
