package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.CreateTeamMapper;
import com.github.ArghgrA.Hackhub.dto.mapper.SubscribeTeamMapper;
import com.github.ArghgrA.Hackhub.dto.request.CreateTeamRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.SubscribeTeamToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.CreateTeamResponseDTO;
import com.github.ArghgrA.Hackhub.dto.response.SubscribeTeamToHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.exception.AlreadyExistingException;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateEnum;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
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
    private final HackathonRepository<DefaultHackathon> hackathonRepository;
    private final CreateTeamMapper createTeamMapper;
    private final SubscribeTeamMapper subscribeTeamMapper;

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


        return createTeamMapper.toResponse(newTeam);
    }

    public SubscribeTeamToHackathonResponseDTO subscribeTeam(SubscribeTeamToHackathonRequestDTO dto){
        DefaultHackathon hackathon = hackathonRepository
                        .findById(dto.idHackathon())
                        .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        if(hackathon.getState() != HackathonStateEnum.REGISTRATION.getInstance()){
            throw new IllegalStateException("Hackathon has not opened registration");
        }

        DefaultTeam team = teamRepository
                .findById(dto.idTeam())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        if(teamRepository.isParticipating(team.getId(), hackathon.getId())){
            throw new AlreadyExistingException("Team is already in the Hackathon");
        }

        team.addHackthon(hackathon);
        hackathon.addTeam(team);

        teamRepository.save(team);
        hackathonRepository.save(hackathon);

        return subscribeTeamMapper.toResponse(team);
    }
}
