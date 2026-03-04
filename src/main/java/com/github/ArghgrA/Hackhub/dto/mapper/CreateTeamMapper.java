package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.response.CreateTeamResponseDTO;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateTeamMapper {
    CreateTeamResponseDTO toResponse(AbstractTeam team);
}
