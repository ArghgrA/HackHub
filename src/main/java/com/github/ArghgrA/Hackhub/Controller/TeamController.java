package com.github.ArghgrA.Hackhub.Controller;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addTeamDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.TeamResponseDTO;
import com.github.ArghgrA.Hackhub.handler.TeamHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {
    private TeamHandler teamHandler;

    public TeamController(TeamHandler teamHandler) {
        this.teamHandler = teamHandler;
    }

    @PostMapping
    public ResponseEntity<TeamResponseDTO> createStaff(@RequestBody addTeamDTO dto) {
        return ResponseEntity.ok(
                teamHandler.createTeam(dto)
        );
    }
}
