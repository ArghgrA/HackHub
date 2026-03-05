package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.InviteUserMapper;
import com.github.ArghgrA.Hackhub.dto.request.InviteUserRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.InviteUserResponseDTO;
import com.github.ArghgrA.Hackhub.exception.AlreadyExistingException;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.other.invites.DefaultInvite;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.repository.InviteRepository;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InviteHandler {
    private final UserRepository<DefaultUser> userRepository;
    private final TeamRepository<DefaultTeam> teamRepository;
    private final InviteRepository<DefaultInvite> inviteRepository;
    private final InviteUserMapper inviteMapper;

    public InviteUserResponseDTO inviteUser(InviteUserRequestDTO request) {
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
        newInvite.setUser(user);
        newInvite.setTeam(team);
        newInvite.setMessage(request.message());

        // persist in db
        inviteRepository.save(newInvite);

        return inviteMapper.toResponse(newInvite);
    }
}
