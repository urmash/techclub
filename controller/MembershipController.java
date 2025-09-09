package com.example.controller;

import com.example.dto.MembershipResponseDTO;
import com.example.model.Membership;
import com.example.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/membership")
@RequiredArgsConstructor
public class MembershipController {

    // Eesti locale kuupäevavorming
    private static final DateTimeFormatter EE_VORMING =
            DateTimeFormatter.ofPattern("d. MMMM yyyy, HH:mm", new Locale("et"));
    private final MembershipService membershipService;

    @PostMapping("/club/{hobbyGroupId}/participant/{participantId}")
    public MembershipResponseDTO joinClub(@PathVariable Long hobbyGroupId,
                                          @PathVariable Long participantId) {
        Membership membership = membershipService.joinClub(hobbyGroupId, participantId);
        return convertToDTO(membership);
    }

    @DeleteMapping("/club/{hobbyGroupId}/participant/{participantId}")
    public void leaveClub(@PathVariable Long hobbyGroupId,
                          @PathVariable Long participantId) {
        membershipService.leaveClub(hobbyGroupId, participantId);
    }

    @GetMapping("/club/{hobbyGroupId}")
    public List<MembershipResponseDTO> getClubMembers(@PathVariable Long hobbyGroupId) {
        return membershipService.getClubMembers(hobbyGroupId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/participant/{participantId}")
    public List<MembershipResponseDTO> getParticipantsClubs(@PathVariable Long participantId) {
        return membershipService.getParticipantsClubs(participantId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MembershipResponseDTO convertToDTO(Membership membership) {
        return new MembershipResponseDTO(
                membership.getId(),                                // komposiitvõti või null
                membership.getParticipant().getFullName(),        // liige
                membership.getHobbyGroup().getName(),             // grupi nimi
                membership.getJoinedAt().format(EE_VORMING)       // eesti keeles kuupäev+kellaaeg
        );
    }
}
