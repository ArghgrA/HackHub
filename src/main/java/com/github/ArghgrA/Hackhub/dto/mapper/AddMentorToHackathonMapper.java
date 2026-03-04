package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.response.AddMentorToHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddMentorToHackathonMapper {
    AddMentorToHackathonResponseDTO toResponse(Mentor mentor);
}
