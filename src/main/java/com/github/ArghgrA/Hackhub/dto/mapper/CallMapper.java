package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.CallDTO;
import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CallMapper {
        @Mapping(source = "sender.id", target = "mentorId")
        @Mapping(source = "receiver.id", target = "teamId")
        @Mapping(source = "message", target = "date")
        @Mapping(source = "ticket.id", target = "ticketId")
        CallDTO toDTO(DefaultCall call);
        List<CallDTO> toDTOList(List<DefaultCall> calls);
}

