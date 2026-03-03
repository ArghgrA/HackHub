package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.Mapper.StaffMapper;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.users.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StaffHandler {
    private UserRepository<AbstractStaff> userRepository;
    HackathonRepository<DefaultHackathon> hackathonRepository;
    private StaffMapper staffMapper;

    public StaffHandler(UserRepository userRepository,
                        HackathonRepository hackathonRepository,
                        StaffMapper staffMapper) {
        this.userRepository = userRepository;
        this.hackathonRepository = hackathonRepository;
        this.staffMapper = staffMapper;
    }

    public StaffResponseDTO createStaff(String role, AddStaffDTO dto) {

        // recupero hackathon dal repository
        AbstractHackathon hackathon = hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new RuntimeException("Hackathon not found"));

        AbstractStaff staff = staffMapper.toEntiy(role,dto);

        // associo l'hackathon
        staff.setHackathon(hackathon);

        //salvo il tutto
        AbstractStaff saved = userRepository.save(staff);

        //ritorno il dto
        return staffMapper.toDTO(saved);
    }

    public void deleteStaff(UUID id) {
        userRepository.deleteById(id);
    }
}
