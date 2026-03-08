package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.InviteDTO;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultInvite;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InviteMapper {
    InviteDTO toDto(DefaultInvite invite);

    List<InviteDTO> toDTOList(List<DefaultInvite> invites);

    DefaultInvite toEntity(InviteDTO dto);
}
