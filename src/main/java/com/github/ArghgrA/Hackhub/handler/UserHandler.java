package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.InviteMapper;
import com.github.ArghgrA.Hackhub.dto.model.InviteDTO;
import com.github.ArghgrA.Hackhub.dto.request.GetInviteRequestDTO;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHandler {
    private final UserRepository<DefaultUser> userRepository;
    private final InviteMapper inviteMapper;

    public List<InviteDTO> getInvite(GetInviteRequestDTO dto) {
        DefaultUser user = userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("No User with that Id"));

        return inviteMapper.toDTOList(user.getInvites());
    }
}
