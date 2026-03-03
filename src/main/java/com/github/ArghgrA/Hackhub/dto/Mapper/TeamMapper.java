package com.github.ArghgrA.Hackhub.dto.Mapper;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addTeamDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.TeamResponseDTO;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.users.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.users.staff.Organizer;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    // Da DTO di creazione a Entity
    public  AbstractTeam toEntiy(addTeamDTO dto){
        AbstractTeam team = new DefaultTeam();
        team.setName(dto.name());
        return team;
    }

    // Da Entity a DTO di risposta
    public TeamResponseDTO toDTO(AbstractTeam team) {
            return new TeamResponseDTO(team.getId());
    }
}
