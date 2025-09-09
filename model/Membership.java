package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "membership")
public class Membership {

    @EmbeddedId
    private MembershipId id;

    @ManyToOne
    @MapsId("hobbyGroupId")
    @JoinColumn(name = "hobby_group_id", nullable = false)
    private HobbyGroup hobbyGroup;

    @ManyToOne
    @MapsId("participantId")
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;
}
