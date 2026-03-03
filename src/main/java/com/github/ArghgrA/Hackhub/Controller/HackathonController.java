package com.github.ArghgrA.Hackhub.Controller;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addHackathonDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addJudgeDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addMentorDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.HackathonResponseDTO;
import com.github.ArghgrA.Hackhub.handler.HackathonHandler;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hackathon")
public class HackathonController {
    private HackathonHandler hackathonHandler;

    public HackathonController(HackathonHandler hackathonHandler) {
        this.hackathonHandler = hackathonHandler;
    }

    @PostMapping
    public ResponseEntity<HackathonResponseDTO> createHackathon(@RequestBody addHackathonDTO dto){
        return ResponseEntity.ok(
                hackathonHandler.creaHackathon(dto)
        );
    }

    @PostMapping("/judge")
    public ResponseEntity<String> addJudgeToHackathon(@RequestBody addJudgeDTO dto){
        hackathonHandler.addJudgeToHackathon(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Judge added to hackathon successfully");
    }

    @PostMapping("/mentor")
    public ResponseEntity<String> addJudgeToHackathon(@RequestBody addMentorDTO dto){
        hackathonHandler.addMentorToHackathon(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Mentor added to hackathon successfully");
    }
}
