package com.example.dto;

import java.time.LocalDateTime;

public class MembershipDTO {

    private Long clubId;
    private Long participantId;
    private LocalDateTime joinedAt;

    public MembershipDTO() {
    }

    public MembershipDTO(Long clubId, Long participantId, LocalDateTime joinedAt) {
        this.clubId = clubId;
        this.participantId = participantId;
        this.joinedAt = joinedAt;
    }

    // Getterid ja setterid
    public Long getClubId() { return clubId; }
    public void setClubId(Long clubId) { this.clubId = clubId; }

    public Long getParticipantId() { return participantId; }
    public void setParticipantId(Long participantId) { this.participantId = participantId; }

    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}
