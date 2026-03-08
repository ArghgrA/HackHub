package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.HackathonDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HackathonMapper {
    HackathonDTO toDTO(DefaultHackathon hackathon);
    List<HackathonDTO> toDTOList(List<DefaultHackathon> hackathons);
    DefaultHackathon toEntity(HackathonDTO dto);
}
