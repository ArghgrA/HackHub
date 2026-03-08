package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.response.CreateHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateHackathonMapper {
    CreateHackathonResponseDTO toResponse(AbstractHackathon hackathon);
}
