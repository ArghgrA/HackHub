package com.github.ArghgrA.Hackhub.dto.Mapper;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addHackathonDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.HackathonResponseDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.TeamResponseDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import org.springframework.stereotype.Component;

@Component
public class HackathonMapper {
    // Da DTO di creazione a Entity
    public AbstractHackathon toEntiy(addHackathonDTO dto){
        AbstractHackathon team = new DefaultHackathon();
        team.setName(dto.name());
        team.setRule(dto.rule());
        team.setMaxTeamMembers(dto.maxTeamMembers());
        team.setIntervallo(
                new Interval(
                        dto.inizioIscrizione(),
                        dto.fineIscrizione(),
                        dto.inizioCompetizione(),
                        dto.fineCompetizione()
                )
        );
        return team;
    }

    // Da Entity a DTO di risposta
    public HackathonResponseDTO toDTO(AbstractHackathon hackathon) {
        return new HackathonResponseDTO(hackathon.getId());
    }
}
