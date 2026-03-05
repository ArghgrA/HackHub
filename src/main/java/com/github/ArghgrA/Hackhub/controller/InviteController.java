package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.request.InviteUserRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.InviteUserResponseDTO;
import com.github.ArghgrA.Hackhub.handler.InviteHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/invite")
public class InviteController {
    @Autowired
    private InviteHandler inviteHandler;

    @PostMapping("/new")
    public ResponseEntity<InviteUserResponseDTO> addInvite(@Valid @RequestBody InviteUserRequestDTO dto) {
        InviteUserResponseDTO response = inviteHandler.inviteUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
