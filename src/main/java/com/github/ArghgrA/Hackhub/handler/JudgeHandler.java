package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.Mapper.JudgeOldMapper;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class JudgeHandler {
    private UserRepository<Judge> judgeRepository;
    private JudgeOldMapper judgeOldMapper;

    public JudgeHandler(UserRepository<Judge> judgeRepository, JudgeOldMapper judgeOldMapper) {
        this.judgeRepository = judgeRepository;
        this.judgeOldMapper = judgeOldMapper;
    }

    public StaffResponseDTO createJudge(AddStaffDTO dto) {
        Judge judge = judgeOldMapper.toEntity(dto);

        judge.setPassword(dto.password());

        Judge saved = judgeRepository.save(judge);
        return judgeOldMapper.toDTO(saved);
    }

    //Qui andremo a fare le altre operazioni CRUD che coinvolgono il Judge
}
