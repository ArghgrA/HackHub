package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.*;
import com.github.ArghgrA.Hackhub.dto.model.*;
import com.github.ArghgrA.Hackhub.dto.request.*;
import com.github.ArghgrA.Hackhub.exception.AlreadyExistingException;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultInvite;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import com.github.ArghgrA.Hackhub.model.other.message.call.facade.CalendarFacade;
import com.github.ArghgrA.Hackhub.model.other.message.ticket.DefaultTicket;
import com.github.ArghgrA.Hackhub.model.other.payment.address.AbstractPaymentAddress;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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
    private final PaymentRepository<AbstractPaymentAddress> paymentRepository;
    private final CallRepository<DefaultCall> callRepository;

    private final TeamMapper teamMapper;
    private final TicketMapper ticketMapper;
    private final SubmissionMapper submissionMapper;
    private final InviteMapper inviteMapper;
    private final PaymentMapper paymentMapper;
    private final CallMapper callMapper;

    // add CalendarFacade Singleton
    private final CalendarFacade calendar = CalendarFacade.getINSTANCE();

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

        // add invite to user list
        user.addInvite(newInvite);

        // persist in db
        inviteRepository.save(newInvite);
        return inviteMapper.toDto(newInvite);
    }

    public void subscribeTeam(SubscribeTeamRequestDTO dto){
        // retrieve hackathon from db
        DefaultHackathon hackathon = hackathonRepository
                        .findById(dto.hackathonId())
                        .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        // check if hackathon accept registration
        if(hackathon.getState() != HackathonStateKind.REGISTRATION.getInstance()){
            throw new IllegalStateException("Hackathon has not opened registration");
        }

        // retrieve team from db
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        // check if team is already participating in the hackathon
        if(teamRepository.isParticipating(team.getId(), hackathon.getId())){
            throw new AlreadyExistingException("Team is already in the Hackathon");
        }

        // add team to hackathon
        team.addHackathon(hackathon);

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
        //nell'if non va messo || ma &&
        if(hackathon.getState() != HackathonStateKind.REGISTRATION.getInstance() &&
           hackathon.getState() != HackathonStateKind.COMPETITION.getInstance() ) {
            throw new IllegalStateException(String.format("cannot open new ticket in %s state",hackathon.getState().getName()));
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
        if(hackathon.getState() != HackathonStateKind.COMPETITION.getInstance() ) {
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

    public PaymentDTO addPayment(AddPaymentMethodRequestDTO dto) {
        // retrieve team from db
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No team with that id"));

        // create payment
        AbstractPaymentAddress payment = dto.kind().getAddressInstance();
        payment.addAddress(dto.address());


        //set team_id to payment
        payment.setTeam(team);
        // save payment in db
        paymentRepository.save(payment);

        // add payment to team
        team.addPaymentAddress(payment);

        return paymentMapper.toDTO(payment);
    }

    // In TeamHandler.java
    public List<CallDTO> getCalls(GetCallRequestDTO dto) {
        // Retrieve team from the database
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        // Retrieve hackathon from the database
        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        // Retrieve calls associated with the team and hackathon
        List<DefaultCall> calls = callRepository.findByTeamAndHackathon(team.getId(), hackathon.getId());

        // Map the calls to CallDTO
        return callMapper.toDTOList(calls);
    }

    public CallDTO acceptCall(AcceptCallRequestDTO dto) {
        // Retrieve call from the database
        DefaultCall call = callRepository.findById(dto.callId())
                .orElseThrow(() -> new EntityNotFoundException("No Call with that id"));

        // Check if the call is already accepted
        if (call.getCalendarEventId() != null) {
            throw new IllegalStateException("Call has already been accepted");
        }

        // Accept the call and create a calendar event
        String eventId = calendar.acceptCall(call);
        call.setCalendarEventId(eventId);
        callRepository.save(call);
        return callMapper.toDTO(call);
    }

    public List<TicketDTO> getTicket(GetTicketRequestTeamDTO dto) {
        TeamMember teamMember = teamMemberRepository
                .findById(dto.teamMemberId())
                .orElseThrow(() -> new EntityNotFoundException("No Member with that id"));

        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        return ticketMapper
                .toDTOList(ticketRepository.findByTeam(team.getId()));
    }

    public void leaveTeam(LeaveTeamRequestDTO dto) {
        // retrieve team member from db
        TeamMember teamMember = teamMemberRepository
                .findById(dto.teamMemberId())
                .orElseThrow(() -> new EntityNotFoundException("No Team Member with that id"));

        // retrieve team from db
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        // check if the team member is in the team
        if (!team.getMembers().contains(teamMember)) {
            throw new IllegalStateException("Team Member is not in the Team");
        }

        // remove team member from team
        team.removeMember(teamMember);

        // transform team member in user
        DefaultUser user = teamMember.transform(DefaultUser.class);

        // persist changes in db
        teamRepository.save(team);
        userRepository.delete(user);
    }
}
