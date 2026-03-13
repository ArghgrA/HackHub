package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.TicketDTO;
import com.github.ArghgrA.Hackhub.model.other.message.ticket.DefaultTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "sender.id", target = "teamId")
    @Mapping(source = "receiver.id", target = "hackathonId")
    TicketDTO toDTO(DefaultTicket ticket);

    List<TicketDTO> toDTOList(List<DefaultTicket> tickets);
}
