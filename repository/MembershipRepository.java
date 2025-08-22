package com.example.repository;

import com.example.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Membership.MembershipId> {

    List<Membership> findByClubId(Long clubId);

    List<Membership> findByParticipantId(Long participantId);

    @Modifying
    @Query("DELETE FROM Membership m WHERE m.club.id = :clubId AND m.participant.id = :participantId")
    void deleteByClubIdAndParticipantId(@Param("clubId") Long clubId,
                                        @Param("participantId") Long participantId);

    Optional<Membership> findById(Membership.MembershipId id);
}