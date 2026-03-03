package com.github.ArghgrA.Hackhub.Mapper;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import org.springframework.stereotype.Component;

@Component
public class JudgeMapper {
    // Da DTO di creazione a Entity
    public Judge toEntity(AddStaffDTO dto) {
        Judge judge = new Judge();
        judge.setName(dto.name());
        judge.setUsername(dto.username());
        judge.setEmail(dto.email());
        judge.setPassword(dto.password());
        return judge;
    }

    // Da Entity a DTO di risposta
    public StaffResponseDTO toDTO(Judge judge) {
        return new StaffResponseDTO(
                judge.getId(),
                judge.getName(),
                judge.getUsername(),
                judge.getEmail(),
                judge.getClass().getSimpleName()
        );
    }
}
