package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addTeamDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.TeamResponseDTO;
import com.github.ArghgrA.Hackhub.dto.Mapper.TeamMapper;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamHandler {
    private TeamRepository<AbstractTeam> teamTeamRepository;
    private TeamMapper teamMapper;


    public TeamHandler(TeamRepository<AbstractTeam> teamTeamRepository, TeamMapper teamMapper) {
        this.teamTeamRepository = teamTeamRepository;
        this.teamMapper = teamMapper;
    }

    public TeamResponseDTO createTeam(addTeamDTO dto){
        AbstractTeam team = teamMapper.toEntiy(dto);
        AbstractTeam saved = teamTeamRepository.save(team);
        return teamMapper.toDTO(saved);
    }
}
