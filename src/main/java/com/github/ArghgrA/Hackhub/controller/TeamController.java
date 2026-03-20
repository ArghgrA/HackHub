package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.model.*;
import com.github.ArghgrA.Hackhub.dto.request.*;
import com.github.ArghgrA.Hackhub.handler.TeamHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/team")
public class TeamController {
    @Autowired
    private TeamHandler teamHandler;

    @PostMapping("/new")
    public ResponseEntity<TeamDTO> addTeam(@Valid @RequestBody CreateTeamRequestDTO dto) {
        TeamDTO response = teamHandler.createTeam(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/subscribe")
    public void subscribeTeam(@Valid @RequestBody SubscribeTeamRequestDTO dto){
        teamHandler.subscribeTeam(dto);
    }

    @PostMapping("/ticket/new")
    public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody AddTicketRequestDTO dto) {
        TicketDTO response = teamHandler.createTicket(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/submission/new")
    public ResponseEntity<SubmissionDTO> addSubmission(
            //@Valid @RequestBody AddSubmissionRequestDTO dto
            @RequestParam("teamId") UUID teamId,
            @RequestParam("hackathonId") UUID hackathonId,
            @RequestParam("file") MultipartFile file
    ) {
        AddSubmissionRequestDTO dto = new AddSubmissionRequestDTO(teamId, hackathonId, file);
        SubmissionDTO response = teamHandler.addSubmission(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/invite/new")
    public ResponseEntity<InviteDTO> inviteUser(@Valid @RequestBody InviteUserRequestDTO dto) {
        InviteDTO response = teamHandler.inviteUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/payment/new")
    public ResponseEntity<PaymentDTO> addPayment(@Valid @RequestBody AddPaymentMethodRequestDTO dto) {
        PaymentDTO response = teamHandler.addPayment(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/call/get")
    public ResponseEntity<List<CallDTO>> getReport(
            @Valid @RequestParam UUID teamId, @Valid @RequestParam UUID hackathonId
    ) {
        GetCallRequestDTO dto = new GetCallRequestDTO(teamId, hackathonId);
        List<CallDTO> response = teamHandler.getCalls(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/call/accept")
    public ResponseEntity<CallDTO> acceptCall(@Valid @RequestBody AcceptCallRequestDTO dto) {
        CallDTO response = teamHandler.acceptCall(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ticket/get")
    public ResponseEntity<List<TicketDTO>> geTickets(
            @Valid @RequestParam UUID teamId,
            @Valid @RequestParam UUID teamMemberId
    ) {
        GetTicketRequestTeamDTO dto = new GetTicketRequestTeamDTO(teamId,teamMemberId);
        List<TicketDTO> response = teamHandler.getTicket(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
