package com.example.controller;

import com.example.dto.ParticipantDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Participant;
import com.example.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ResponseEntity<ParticipantDTO> createParticipant(@RequestBody ParticipantDTO participantDTO) {
        Participant participant = convertToEntity(participantDTO);
        Participant savedParticipant = participantService.createParticipant(participant);
        return ResponseEntity.ok(convertToDTO(savedParticipant));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> getAllParticipants() {
        List<ParticipantDTO> participants = participantService.getAllParticipants()
                .stream()
                .map(this::convertToDTO)
                .toList(); // ✅ kasutame Java 16+ toList()
        return ResponseEntity.ok(participants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable Long id) {
        Participant participant = participantService.getParticipantById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));
        return ResponseEntity.ok(convertToDTO(participant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDTO> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDTO participantDTO) {
        Participant updatedParticipant = convertToEntity(participantDTO);
        Participant savedParticipant = participantService.updateParticipant(id, updatedParticipant);
        return ResponseEntity.ok(convertToDTO(savedParticipant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    // --- Helper methods ---
    private ParticipantDTO convertToDTO(Participant participant) {
        return new ParticipantDTO(
                participant.getId(),
                participant.getFirstName(),
                participant.getLastName(),
                participant.getJoinedAt()
        );
    }

    private Participant convertToEntity(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setFirstName(participantDTO.getFirstName());
        participant.setLastName(participantDTO.getLastName());
        participant.setJoinedAt(participantDTO.getJoinedAt());
        return participant;
    }
}
