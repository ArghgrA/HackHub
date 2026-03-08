package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.EvaluationDTO;
import com.github.ArghgrA.Hackhub.model.other.message.evaluation.DefaultEvaluation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {
    EvaluationDTO toDTO(DefaultEvaluation evaluation);
    List<EvaluationDTO> toDTOList(List<DefaultEvaluation> evaluations);
    DefaultEvaluation toEntity(EvaluationDTO dto);
}
