package com.example.controller;

import com.example.dto.ParticipantRequestDTO;
import com.example.dto.ParticipantResponseDTO;
import com.example.mapper.ParticipantMapper;
import com.example.model.Participant;
import com.example.service.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ResponseEntity<ParticipantResponseDTO> createParticipant(@Valid @RequestBody ParticipantRequestDTO dto) {
        Participant saved = participantService.createParticipant(dto);
        return ResponseEntity.ok(ParticipantMapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantResponseDTO>> getAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        List<ParticipantResponseDTO> dtos = participants.stream()
                .map(ParticipantMapper::toDTO)
                .toList(); // returns an unmodifiable List
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResponseDTO> getParticipantById(@PathVariable Long id) {
        Participant participant = participantService.getParticipantById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        return ResponseEntity.ok(ParticipantMapper.toDTO(participant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResponseDTO> updateParticipant(@PathVariable Long id,
                                                                    @Valid @RequestBody ParticipantRequestDTO dto) {
        Participant updated = participantService.updateParticipant(id, dto);
        return ResponseEntity.ok(ParticipantMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
