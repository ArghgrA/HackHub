package com.github.ArghgrA.Hackhub.controller;

import com.github.ArghgrA.Hackhub.dto.request.AddStaffRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.AddStaffResponseDTO;
import com.github.ArghgrA.Hackhub.handler.StaffHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/staff")
public class StaffController {
    @Autowired
    private StaffHandler staffHandler;

    @PostMapping("/new")
    public ResponseEntity<AddStaffResponseDTO> addStaff(@Valid @RequestBody AddStaffRequestDTO dto) {
        AddStaffResponseDTO response = staffHandler.createStaff(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
