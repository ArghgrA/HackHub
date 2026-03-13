package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.HackathonDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HackathonMapper {
    @Mapping(source = "organizer.id", target = "organizerId")
    @Mapping(source = "interval.registrationStart", target = "registrationStart")
    @Mapping(source = "interval.registrationEnd", target = "registrationEnd")
    @Mapping(source = "interval.competitionStart", target = "competitionStart")
    @Mapping(source = "interval.competitionEnd", target = "competitionEnd")
    @Mapping(source = "state.name", target = "state")
    HackathonDTO toDTO(DefaultHackathon hackathon);

    List<HackathonDTO> toDTOList(List<DefaultHackathon> hackathons);
}
