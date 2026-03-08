package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.*;
import com.github.ArghgrA.Hackhub.dto.model.InviteDTO;
import com.github.ArghgrA.Hackhub.dto.model.SubmissionDTO;
import com.github.ArghgrA.Hackhub.dto.model.TeamDTO;
import com.github.ArghgrA.Hackhub.dto.model.TicketDTO;
import com.github.ArghgrA.Hackhub.dto.request.*;
import com.github.ArghgrA.Hackhub.exception.AlreadyExistingException;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateEnum;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultInvite;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultTicket;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamHandler {
    private final UserRepository<DefaultUser> userRepository;
    private final UserRepository<TeamMember> teamMemberRepository;
    private final TeamRepository<DefaultTeam> teamRepository;
    private final InviteRepository<DefaultInvite> inviteRepository;
    private final HackathonRepository<DefaultHackathon> hackathonRepository;
    private final TicketRepository<DefaultTicket> ticketRepository;
    private final SubmissionRepository<DefaultSubmission> submissionRepository;

    private final TeamMapper teamMapper;
    private final TicketMapper ticketMapper;
    private final SubmissionMapper submissionMapper;
    private final InviteMapper inviteMapper;

    public TeamDTO createTeam(CreateTeamRequestDTO dto) {
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


        return teamMapper.toDTO(newTeam);
    }

    public InviteDTO inviteUser(InviteUserRequestDTO request) {
        // check if invited user exist
        DefaultUser user = userRepository
                .findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not exist"));

        // check if team exist
        // ( should never throw error since a TeamMember is always in a Team )
        DefaultTeam team = teamRepository
                .findById(request.teamId())
                .orElseThrow(() -> new EntityNotFoundException("Team not exist"));

        // check if the user was already invited by another TeamMember of the same team
        Optional<DefaultInvite> alreadyExistingInvite = inviteRepository.findInviteByTeam(request.userId(),request.teamId());
        if(alreadyExistingInvite.isPresent()) throw new AlreadyExistingException("User already invited from Team");

        // create new invite with previous data
        DefaultInvite newInvite = new DefaultInvite();
        newInvite.setReceiver(user);
        newInvite.setSender(team);
        newInvite.setMessage(request.message());

        // persist in db
        inviteRepository.save(newInvite);

        return inviteMapper.toDto(newInvite);
    }

    public void subscribeTeam(SubscribeTeamRequestDTO dto){
        // retrieve hackathon from db
        DefaultHackathon hackathon = hackathonRepository
                        .findById(dto.idHackathon())
                        .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        // check if hackathon accept registration
        if(hackathon.getState() != HackathonStateEnum.REGISTRATION.getInstance()){
            throw new IllegalStateException("Hackathon has not opened registration");
        }

        // retrieve team from db
        DefaultTeam team = teamRepository
                .findById(dto.idTeam())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        // check if team is already participating in the hackathon
        if(teamRepository.isParticipating(team.getId(), hackathon.getId())){
            throw new AlreadyExistingException("Team is already in the Hackathon");
        }

        // add team to hackathon
        team.addHackthon(hackathon);
        hackathon.addTeam(team);

        // save in db
        teamRepository.save(team);
        hackathonRepository.save(hackathon);
    }

    public TicketDTO createTicket(AddTicketRequestDTO dto) {
        // retrieve team from db
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        // check if team is not in the hackathon
        if(!teamRepository.isParticipating(dto.teamId(),dto.hackathonId())) {
            throw new IllegalStateException("Team is not in the selected Hackathon");
        }

        // retrieve hackathon from db
        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("no Hackathon with that id"));

        // check if hackathon is in a state where ticket can be opened
        if(hackathon.getState() != HackathonStateEnum.REGISTRATION.getInstance() ||
           hackathon.getState() != HackathonStateEnum.COMPETITION.getInstance() ) {
            throw new IllegalStateException(String.format("cannot open new ticket in %s state",hackathon.getState().toString()));
        }

        // create new ticket
        DefaultTicket ticket = new DefaultTicket();
        ticket.setMessage(dto.message());
        ticket.setSender(team);
        ticket.setReceiver(hackathon);

        // save in db
        ticketRepository.save(ticket);

        return ticketMapper.toDTO(ticket);
    }

    public SubmissionDTO addSubmission(AddSubmissionRequestDTO dto) {
        // retrieve team from db
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        // check if team is not in the hackathon
        if(!teamRepository.isParticipating(dto.teamId(),dto.hackathonId())) {
            throw new IllegalStateException("Team is not in the selected Hackathon");
        }

        // retrieve hackathon from db
        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("no Hackathon with that id"));

        // check if hackathon is in a state where submission can be added
        if(hackathon.getState() != HackathonStateEnum.COMPETITION.getInstance() ) {
            throw new IllegalStateException(String.format("cannot open new ticket in %s state",hackathon.getState().toString()));
        }

        // create new submission
        DefaultSubmission submission = new DefaultSubmission();
        try {
            submission.setMessage(ArrayUtils.toObject(dto.file().getBytes()));
        } catch (IOException ex) { ex.printStackTrace(); }
        submission.setReceiver(hackathon);
        submission.setSender(team);

        // delete already existing submission if exist
        submissionRepository.findByTeam(team.getId()).ifPresent(submissionRepository::delete);

        // save in db
        submissionRepository.save(submission);

        return submissionMapper.toDto(submission);
    }
}
