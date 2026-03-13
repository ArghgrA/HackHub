package com.github.ArghgrA.Hackhub.dto.request;

import com.github.ArghgrA.Hackhub.model.user.staff.util.StaffKind;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddStaffRequestDTO(
        @NotNull(message = "{AddStaffRequestDTO.userId.NotNull}")
        UUID userId,

        @NotNull(message = "{AddStaffRequestDTO.role.NotNull}")
        StaffKind role
) {
}