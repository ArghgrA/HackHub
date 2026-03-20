package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.model.InviteDTO;
import com.github.ArghgrA.Hackhub.dto.request.GetInviteRequestDTO;
import com.github.ArghgrA.Hackhub.handler.UserHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserHandler userHandler;

    @GetMapping("/invite/get")
    public ResponseEntity<List<InviteDTO>> getInvite(
            @Valid @RequestParam UUID userId
            ) {
        GetInviteRequestDTO dto = new GetInviteRequestDTO(userId);
        List<InviteDTO> response = userHandler.getInvite(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
