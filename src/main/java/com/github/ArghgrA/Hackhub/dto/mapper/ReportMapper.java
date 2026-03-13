package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.ReportDTO;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    @Mapping(source = "sender.id", target = "mentorId")
    @Mapping(source = "team.id", target = "teamId")
    @Mapping(source = "receiver.id", target = "hackathonId")
    ReportDTO toDTO(DefaultReport report);
    List<ReportDTO> toDTOList(List<DefaultReport> reports);
}
