package com.github.ArghgrA.Hackhub.Mapper;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.model.abstractions.Staff;
import com.github.ArghgrA.Hackhub.model.users.AbstractUser;
import com.github.ArghgrA.Hackhub.model.users.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.users.staff.Organizer;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    // Da DTO di creazione a Entity
    public AbstractStaff toEntiy(String role, AddStaffDTO dto){
        AbstractStaff staff = switch (role.toLowerCase()) {
            case "judge" -> new Judge();
            case "mentor" -> new Mentor();
            case "organizer" -> new Organizer();
            default -> throw new IllegalArgumentException("Ruolo non valido");
        };

        staff.setName(dto.name());
        staff.setUsername(dto.username());
        staff.setEmail(dto.email());
        staff.setPassword(dto.password());
        return staff;
    }

    // Da Entity a DTO di risposta
    public StaffResponseDTO toDTO(AbstractStaff staff) {
        return new StaffResponseDTO(
                staff.getId(),
                staff.getName(),
                staff.getUsername(),
                staff.getEmail(),
                staff.getClass().getSimpleName()
        );
    }
}
