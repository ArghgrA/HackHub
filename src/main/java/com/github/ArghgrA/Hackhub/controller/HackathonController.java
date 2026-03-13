package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.model.HackathonDTO;
import com.github.ArghgrA.Hackhub.dto.model.StaffDTO;
import com.github.ArghgrA.Hackhub.dto.request.AddJudgeToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.AddMentorToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.CreateHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.handler.HackathonHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hackathon")
public class HackathonController {
    @Autowired
    private HackathonHandler hackathonHandler;

    @PostMapping("/new")
    public ResponseEntity<HackathonDTO> newHackathon(@Valid @RequestBody CreateHackathonRequestDTO dto) {
        HackathonDTO response = hackathonHandler.createHackathon(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add/judge")
    public ResponseEntity<StaffDTO> addJudge(@Valid @RequestBody AddJudgeToHackathonRequestDTO dto) {
        StaffDTO response = hackathonHandler.addJudge(dto);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add/mentor")
    public ResponseEntity<StaffDTO> addMentor(@Valid @RequestBody AddMentorToHackathonRequestDTO dto) {
        StaffDTO response = hackathonHandler.addMentor(dto);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
