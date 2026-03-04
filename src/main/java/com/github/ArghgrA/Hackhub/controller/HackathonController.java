package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.request.AddJudgeToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.AddMentorToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.CreateHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.AddJudgeToHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.dto.response.AddMentorToHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.dto.response.CreateHackathonResponseDTO;
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
    public ResponseEntity<CreateHackathonResponseDTO> newHackathon(@Valid @RequestBody CreateHackathonRequestDTO dto) {
        CreateHackathonResponseDTO response = hackathonHandler.createHackathon(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add/judge")
    public ResponseEntity<AddJudgeToHackathonResponseDTO> addJudge(@Valid @RequestBody AddJudgeToHackathonRequestDTO dto) {
        AddJudgeToHackathonResponseDTO response = hackathonHandler.addJudge(dto);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add/mentor")
    public ResponseEntity<AddMentorToHackathonResponseDTO> addJudge(@Valid @RequestBody AddMentorToHackathonRequestDTO dto) {
        AddMentorToHackathonResponseDTO response = hackathonHandler.addMentor(dto);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
