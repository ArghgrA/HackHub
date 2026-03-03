package com.github.ArghgrA.Hackhub.dto.Mapper;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addJudgeDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import org.springframework.stereotype.Component;

@Component
public class JudgeMapper {
    /**
     * Associa un giudice esistente a un hackathon.
     * L'handler si occuperà del salvataggio.
     */
    public Judge fromDTO(addJudgeDTO dto, Judge judge, AbstractHackathon hackathon) {
        hackathon.addJudge(judge); // gestisce la relazione bidirezionale
        return judge;
    }
}
