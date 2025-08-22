package com.example.controller;

import com.example.dto.ClubDTO;
import com.example.model.Club;
import com.example.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/klubid")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<ClubDTO> createClub(@RequestBody ClubDTO clubDTO) {
        Club club = convertToEntity(clubDTO);
        Club createdClub = clubService.createClub(club);
        return new ResponseEntity<>(convertToDTO(createdClub), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClubDTO>> getAllClubs() {
        List<ClubDTO> clubs = clubService.getAllClubs().stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id) {
        return clubService.getClubById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubDTO> updateClub(
            @PathVariable Long id,
            @RequestBody ClubDTO clubDTO) {
        clubDTO.setId(id); // Tagame, et ID vastab URL-ile
        Club club = convertToEntity(clubDTO);
        Club updatedClub = clubService.updateClub(id, club);
        return ResponseEntity.ok(convertToDTO(updatedClub));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    private ClubDTO convertToDTO(Club club) {
        return new ClubDTO(
                club.getId(),
                club.getName(),
                club.getDescription(),
                club.getCreatedAt()
        );
    }

    private Club convertToEntity(ClubDTO clubDTO) {
        Club club = new Club();
        club.setName(clubDTO.getName());
        club.setDescription(clubDTO.getDescription());
        return club;
    }
}