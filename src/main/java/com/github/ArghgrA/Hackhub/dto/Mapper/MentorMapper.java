package com.github.ArghgrA.Hackhub.dto.Mapper;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addMentorDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import org.springframework.stereotype.Component;

@Component
public class MentorMapper {
    public Mentor fromDTO(addMentorDTO dto, Mentor mentor, AbstractHackathon hackathon) {
        hackathon.addMentor(mentor); // usa il metodo dell'entity
        return mentor;
    }
}
