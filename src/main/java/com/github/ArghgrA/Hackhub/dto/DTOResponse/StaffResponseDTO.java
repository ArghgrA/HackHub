package com.github.ArghgrA.Hackhub.dto.DTOResponse;

import java.util.UUID;

public record StaffResponseDTO(
        UUID id,
        String name,
        String username,
        String email,

        //Aggiunto dopo non so se ci va
        String role
){}
