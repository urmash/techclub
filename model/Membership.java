package com.example.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "membership")
@IdClass(Membership.MembershipId.class)
public class Membership {

    @Id
    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false) // ingliskeelne veerunimi
    private Club club;

    @Id
    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false) // ingliskeelne veerunimi
    private Participant participant;

    @Column(name = "joined_at", nullable = false, updatable = false) // ingliskeelne veerunimi
    private LocalDateTime joinedAt = LocalDateTime.now();

    // --- Liitvõtme klass ---
    public static class MembershipId implements Serializable {
        private Long club;
        private Long participant;

        public MembershipId() {}

        public MembershipId(Long club, Long participant) {
            this.club = club;
            this.participant = participant;
        }

        public Long getClub() { return club; }
        public void setClub(Long club) { this.club = club; }
        public Long getParticipant() { return participant; }
        public void setParticipant(Long participant) { this.participant = participant; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MembershipId)) return false;
            MembershipId that = (MembershipId) o;
            return Objects.equals(club, that.club) &&
                    Objects.equals(participant, that.participant);
        }

        @Override
        public int hashCode() {
            return Objects.hash(club, participant);
        }
    }

    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }

    public Participant getParticipant() { return participant; }
    public void setParticipant(Participant participant) { this.participant = participant; }

    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}
