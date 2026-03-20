package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.model.*;
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
            @Valid @RequestParam UUID hackathonId
    ) {
        GetTicketRequestStaffDTO dto = new GetTicketRequestStaffDTO(hackathonId);
        List<TicketDTO> response = staffHandler.getTicket(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/ticket/close")
    public void closeTicket(@Valid @RequestBody CloseTicketRequestDTO dto) {
        staffHandler.closeTicket(dto);
    }

    @GetMapping("/evaluation/get")
    public ResponseEntity<List<EvaluationDTO>> getEvaluations(
            @Valid @RequestParam UUID hackathonId
    ) {
        GetEvaluationRequestDTO dto = new GetEvaluationRequestDTO(hackathonId);
        List<EvaluationDTO> response = staffHandler.getEvaluation(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/evaluation/end")
    public void endEvaluation(@Valid @RequestBody EndEvaluationRequestDTO dto) {
        staffHandler.endEvaluation(dto);
    }

    @GetMapping("/report/get")
    public ResponseEntity<List<ReportDTO>> getReport(
            @Valid @RequestParam UUID hackathonId
    ) {
        GetReportRequestDTO dto = new GetReportRequestDTO(hackathonId);
        List<ReportDTO> response = staffHandler.getReport(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/proclaim")
    public void proclaimTeam(@Valid @RequestBody ProclaimTeamRequestDTO dto){
        staffHandler.proclaimTeam(dto);
    }

    @PostMapping("/call/new")
    public ResponseEntity<CallDTO> addCall(@Valid @RequestBody AddCallRequestDTO dto) {
        CallDTO response = staffHandler.createCall(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/submission/get")
    public ResponseEntity<List<SubmissionDTO>> getSubmission(
            @Valid @RequestParam UUID hackathonId
    ) {
        GetSubmissionRequestDTO dto = new GetSubmissionRequestDTO(hackathonId);
        List<SubmissionDTO> response = staffHandler.getSubmission(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
