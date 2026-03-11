package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.model.EvaluationDTO;
import com.github.ArghgrA.Hackhub.dto.model.ReportDTO;
import com.github.ArghgrA.Hackhub.dto.model.StaffDTO;
import com.github.ArghgrA.Hackhub.dto.model.TicketDTO;
import com.github.ArghgrA.Hackhub.dto.request.*;
import com.github.ArghgrA.Hackhub.handler.StaffHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/staff")
public class StaffController {
    @Autowired
    private StaffHandler staffHandler;

    @PostMapping("/new")
    public ResponseEntity<StaffDTO> addStaff(@Valid @RequestBody AddStaffRequestDTO dto) {
        StaffDTO response = staffHandler.createStaff(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/report/team")
    public ResponseEntity<ReportDTO> reportTeam(@Valid @RequestBody ReportTeamRequestDTO dto) {
        ReportDTO response = staffHandler.reportTeam(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("evaluation/new")
    public ResponseEntity<EvaluationDTO> addEvaluation(@Valid @RequestBody AddEvaluationRequestDTO dto) {
        EvaluationDTO response = staffHandler.addEvaluation(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ticket/get")
    public ResponseEntity<List<TicketDTO>> geTickets(
            //@RequestParam GetTicketRequestDTO dto
            @RequestParam UUID hackathonId
    ) {
        GetTicketRequestDTO dto = new GetTicketRequestDTO(hackathonId);
        List<TicketDTO> response = staffHandler.getTicket(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/ticket/delete")
    public void closeTicket(CloseTicketRequestDTO dto) {
        staffHandler.closeTicket(dto);
    }
}
