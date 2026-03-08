package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.ReportDTO;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultReport;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportDTO toDTO(DefaultReport report);
    List<ReportDTO> toDTOList(List<DefaultReport> reports);
    DefaultReport toEntity(ReportDTO dto);
}
