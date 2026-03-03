package com.github.ArghgrA.Hackhub.dto.DTOCreazione;

import java.util.UUID;

public record AddStaffDTO(
        String name,
        String username,
        String email,
        String password,
        //aggiuto vediamo se va tolto
        UUID hackathonId
){}
