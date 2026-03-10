package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.EvaluationDTO;
import com.github.ArghgrA.Hackhub.model.other.message.evaluation.DefaultEvaluation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {
    @Mapping(source = "submission.id", target = "submissionId")
    @Mapping(source = "sender.id", target = "judgeId")
    @Mapping(source = "message.score", target = "score")
    @Mapping(source = "message.details", target = "details")
    EvaluationDTO toDTO(DefaultEvaluation evaluation);

    List<EvaluationDTO> toDTOList(List<DefaultEvaluation> evaluations);
}
