package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.InviteDTO;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultInvite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InviteMapper {
    @Mapping(source = "sender.id",target = "teamId")
    @Mapping(source = "receiver.id",target = "userId")
    InviteDTO toDto(DefaultInvite invite);

    List<InviteDTO> toDTOList(List<DefaultInvite> invites);
}
