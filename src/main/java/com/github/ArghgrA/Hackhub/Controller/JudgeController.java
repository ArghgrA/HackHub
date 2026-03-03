package com.github.ArghgrA.Hackhub.Controller;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.handler.JudgeHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/judge")
public class JudgeController {
    private final JudgeHandler judgeService;

    public JudgeController(JudgeHandler judgeService) {
        this.judgeService = judgeService;
    }

    @PostMapping
    public ResponseEntity<StaffResponseDTO> createJudge(@RequestBody AddStaffDTO dto) {
        StaffResponseDTO response = judgeService.createJudge(dto);
        return ResponseEntity.ok(response);
    }
}
