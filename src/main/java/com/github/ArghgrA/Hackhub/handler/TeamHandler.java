package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.CreateTeamMapper;
import com.github.ArghgrA.Hackhub.dto.request.CreateTeamRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.CreateTeamResponseDTO;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamHandler {
    private final UserRepository<DefaultUser> userRepository;
    private final UserRepository<TeamMember> teamMemberRepository;
    private final TeamRepository<DefaultTeam> teamRepository;
    private final CreateTeamMapper teamMapper;

    public CreateTeamResponseDTO createTeam(CreateTeamRequestDTO dto) {
        // create new Team with given info
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setName(dto.name());

        // retrieve User from db
        DefaultUser user = userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("No User with that id"));

        // transform User in TeamMember
        TeamMember teamMember = user.transform(TeamMember.class);
        teamMember.setTeam(newTeam);
        newTeam.addMember(teamMember);

        // drop User from db
        userRepository.delete(user);

        // persist newTeam and newTeamMember in db
        teamRepository.save(newTeam);
        teamMemberRepository.save(teamMember);


        return teamMapper.toResponse(newTeam);
    }
}
