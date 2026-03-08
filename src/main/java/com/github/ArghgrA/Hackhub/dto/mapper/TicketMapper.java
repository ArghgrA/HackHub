package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.TicketDTO;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultTicket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDTO toDTO(DefaultTicket ticket);

    List<TicketDTO> toDTOList(List<DefaultTicket> tickets);

    DefaultTicket toEntity(TicketDTO dto);
}
