package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.CreateTeamMapper;
import com.github.ArghgrA.Hackhub.dto.request.CreateTeamRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.CreateTeamResponseDTO;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamHandler {
    private final TeamRepository<DefaultTeam> teamRepository;
    private final CreateTeamMapper teamMapper;

    public CreateTeamResponseDTO createTeam(CreateTeamRequestDTO dto) {
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setName(dto.name());
        teamRepository.save(newTeam);
        return teamMapper.toResponse(newTeam);
    }
}
