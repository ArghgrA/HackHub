package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.CallDTO;
import com.github.ArghgrA.Hackhub.dto.model.TeamMemberDTO;
import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMemberMapper{
    TeamMemberDTO toDTO(TeamMember teamMember);
    List<TeamMemberDTO> toDTOList(List<TeamMember> teamMembers);
}
