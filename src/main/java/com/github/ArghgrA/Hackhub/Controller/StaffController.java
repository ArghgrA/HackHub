package com.github.ArghgrA.Hackhub.Controller;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.AddStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.DeleteStaffDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.StaffResponseDTO;
import com.github.ArghgrA.Hackhub.handler.StaffHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private StaffHandler staffHandler;

    public StaffController(StaffHandler staffHandler) {
        this.staffHandler = staffHandler;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<StaffResponseDTO> createStaff(@RequestParam String role, @RequestBody AddStaffDTO dto) {
        return ResponseEntity.ok(
                staffHandler.createStaff(role, dto)
        );
    }

    // DELETE
    @DeleteMapping
    public ResponseEntity<String> deleteStaff(@RequestBody DeleteStaffDTO deleteStaffDTO) {
        staffHandler.deleteStaff(deleteStaffDTO.id());
        return ResponseEntity.ok("Staff eliminato con successo");
    }

    /*

    // GET ALL
    @GetMapping
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> getStaffById(@PathVariable UUID id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }
     */
}
