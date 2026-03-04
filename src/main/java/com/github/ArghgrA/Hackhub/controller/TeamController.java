package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.request.CreateTeamRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.CreateTeamResponseDTO;
import com.github.ArghgrA.Hackhub.handler.TeamHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/team")
public class TeamController {
    @Autowired
    private TeamHandler teamHandler;

    @PostMapping("/add")
    public ResponseEntity<CreateTeamResponseDTO> addTeam(@Valid @RequestBody CreateTeamRequestDTO dto) {
        CreateTeamResponseDTO response = teamHandler.createTeam(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
