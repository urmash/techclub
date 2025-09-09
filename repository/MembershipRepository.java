package com.example.repository;

import com.example.model.Membership;
import com.example.model.MembershipId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, MembershipId> {

    Optional<Membership> findById(MembershipId id);

    // leia kõik liikmesused HobbyGroup ID järgi
    List<Membership> findByHobbyGroup_Id(Long hobbyGroupId);

    // leia kõik liikmesused konkreetse osaleja ID järgi
    List<Membership> findByParticipant_Id(Long participantId);

    // kustuta liikmesus konkreetse HobbyGroup ja osaleja järgi
    void deleteByHobbyGroup_IdAndParticipant_Id(Long hobbyGroupId, Long participantId);
}
