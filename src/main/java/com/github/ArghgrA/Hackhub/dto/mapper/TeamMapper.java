package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.TeamDTO;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    TeamDTO toDTO(DefaultTeam team);

    List<DefaultTeam> toDTOList(List<DefaultTeam> teams);

    DefaultTeam toEntity(TeamDTO dto);
}
