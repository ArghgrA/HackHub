package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.InviteMapper;
import com.github.ArghgrA.Hackhub.dto.mapper.TeamMemberMapper;
import com.github.ArghgrA.Hackhub.dto.model.InviteDTO;
import com.github.ArghgrA.Hackhub.dto.model.TeamMemberDTO;
import com.github.ArghgrA.Hackhub.dto.request.AcceptInviteRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.GetInviteRequestDTO;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.abstraction.Team;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultInvite;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.repository.InviteRepository;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHandler {
    private final UserRepository<DefaultUser> userRepository;
    private final InviteRepository<DefaultInvite> inviteRepository;
    private final TeamRepository<DefaultTeam> teamRepository;
    private final UserRepository<TeamMember> teamMemberRepository;

    private final InviteMapper inviteMapper;
    private final TeamMemberMapper teamMemberMapper;


    public List<InviteDTO> getInvite(GetInviteRequestDTO dto) {
        DefaultUser user = userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("No User with that Id"));

        return inviteMapper.toDTOList(user.getInvites());
    }

    public TeamMemberDTO acceptInvite(AcceptInviteRequestDTO dto) {
        DefaultInvite invite = inviteRepository
                .findById(dto.inviteId())
                .orElseThrow(() -> new EntityNotFoundException("No Invite with that Id"));

        DefaultUser user = userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("No User with that Id"));

        DefaultTeam team = (DefaultTeam) invite.getSender();

        inviteRepository.delete(invite);

        TeamMember teamMember = user.transform(TeamMember.class);
        team.addMember(teamMember);

        userRepository.delete(user);

        teamMemberRepository.save(teamMember);

        return teamMemberMapper.toDTO(teamMember);
    }
}
