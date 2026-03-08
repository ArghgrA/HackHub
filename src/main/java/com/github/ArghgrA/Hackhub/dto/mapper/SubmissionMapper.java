package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.SubmissionDTO;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {
    SubmissionDTO toDto(DefaultSubmission submission);

    List<SubmissionDTO> toDTOList(List<DefaultSubmission> submissions);

    DefaultSubmission toEntity(SubmissionDTO dto);
}
