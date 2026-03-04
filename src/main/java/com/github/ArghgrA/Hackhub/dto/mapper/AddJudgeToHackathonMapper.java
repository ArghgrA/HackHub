package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.response.AddJudgeToHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddJudgeToHackathonMapper {
    AddJudgeToHackathonResponseDTO toResponse(Judge judge);
}
