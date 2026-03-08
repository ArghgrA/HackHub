package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.response.InviteUserResponseDTO;
import com.github.ArghgrA.Hackhub.model.other.invites.AbstractInvite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InviteUserMapper {
    InviteUserResponseDTO toResponse(AbstractInvite invite);
}
