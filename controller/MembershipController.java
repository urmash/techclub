package com.example.controller;

import com.example.dto.MembershipDTO;
import com.example.model.Membership;
import com.example.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership")
public class MembershipController {

    private final MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    // Liitumine klubiga
    @PostMapping
    public ResponseEntity<MembershipDTO> joinClub(@RequestBody MembershipDTO membershipDTO) {
        Membership membership = membershipService.joinClub(
                membershipDTO.getClubId(),
                membershipDTO.getParticipantId()
        );
        return new ResponseEntity<>(convertToDTO(membership), HttpStatus.CREATED);
    }

    // Klubist lahkumine
    @DeleteMapping
    public ResponseEntity<Void> leaveClub(@RequestBody MembershipDTO membershipDTO) {
        membershipService.leaveClub(
                membershipDTO.getClubId(),
                membershipDTO.getParticipantId()
        );
        return ResponseEntity.noContent().build();
    }

    // Klubi liikmete pärimine
    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<MembershipDTO>> getClubMembers(@PathVariable Long clubId) {
        List<MembershipDTO> members = membershipService.getClubMembers(clubId).stream()
                .map(this::convertToDTO)
                .toList(); // kasutame Java 16+ toList() meetodit
        return ResponseEntity.ok(members);
    }

    // Osaleja klubide pärimine
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<MembershipDTO>> getParticipantsClubs(@PathVariable Long participantId) {
        List<MembershipDTO> clubs = membershipService.getParticipantsClubs(participantId).stream()
                .map(this::convertToDTO)
                .toList(); // samuti toList()
        return ResponseEntity.ok(clubs);
    }

