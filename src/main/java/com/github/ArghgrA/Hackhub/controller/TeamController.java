package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.model.*;
import com.github.ArghgrA.Hackhub.dto.request.*;
import com.github.ArghgrA.Hackhub.handler.TeamHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SubmissionDTO> addSubmission(@Valid @RequestBody AddSubmissionRequestDTO dto) {
        SubmissionDTO response = teamHandler.addSubmission(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/invite/new")
    public ResponseEntity<InviteDTO> inviteUser(InviteUserRequestDTO dto) {
        InviteDTO response = teamHandler.inviteUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/payment/new")
    public ResponseEntity<PaymentDTO> addPayment(AddPaymentMethodRequestDTO dto) {
        PaymentDTO response = teamHandler.addPayment(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
