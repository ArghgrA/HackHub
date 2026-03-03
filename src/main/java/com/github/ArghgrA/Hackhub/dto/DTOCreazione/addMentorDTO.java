package com.github.ArghgrA.Hackhub.dto.DTOCreazione;

import java.util.UUID;

public record addMentorDTO(
        UUID mentorId,
        UUID hackathonId
) {
}
