package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.Mapper.JudgeMapper;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class JudgeHandler {
    private UserRepository<Judge> judgeRepository;
    private JudgeMapper judgeMapper;

    public JudgeHandler(UserRepository<Judge> judgeRepository, JudgeMapper judgeMapper) {
        this.judgeRepository = judgeRepository;
        this.judgeMapper = judgeMapper;
    }

    public StaffResponseDTO createJudge(AddStaffDTO dto) {
        Judge judge = judgeMapper.toEntity(dto);

        judge.setPassword(dto.password());

        Judge saved = judgeRepository.save(judge);
        return judgeMapper.toDTO(saved);
    }

    //Qui andremo a fare le altre operazioni CRUD che coinvolgono il Judge
}
