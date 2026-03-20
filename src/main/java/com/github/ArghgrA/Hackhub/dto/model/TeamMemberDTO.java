package com.github.ArghgrA.Hackhub.dto.model;

import java.util.UUID;

public record TeamMemberDTO(
        UUID teamMemberId,
        UUID hackathonId
) {
}
