package com.github.ArghgrA.Hackhub.dto.model;

import com.github.ArghgrA.Hackhub.model.user.staff.util.StaffEnum;

import java.util.UUID;

public record StaffDTO(
        UUID id,
        UUID hackathonId,
        String email,
        String role
) {
}
