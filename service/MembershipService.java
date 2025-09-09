package com.example.service;

import com.example.model.HobbyGroup;
import com.example.model.Membership;
import com.example.model.MembershipId;
import com.example.model.Participant;
import com.example.repository.HobbyGroupRepository;
import com.example.repository.MembershipRepository;
import com.example.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final HobbyGroupRepository hobbyGroupRepository;
    private final ParticipantRepository participantRepository;

    public Membership joinClub(Long hobbyGroupId, Long participantId) {
        HobbyGroup group = hobbyGroupRepository.findById(hobbyGroupId)
                .orElseThrow(() -> new RuntimeException("HobbyGroup not found with id: " + hobbyGroupId));
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found with id: " + participantId));

        Membership membership = new Membership();
        membership.setHobbyGroup(group);
        membership.setParticipant(participant);

        return membershipRepository.save(membership);
    }

    public void leaveClub(Long hobbyGroupId, Long participantId) {
        membershipRepository.deleteByHobbyGroup_IdAndParticipant_Id(hobbyGroupId, participantId);
    }

    public List<Membership> getClubMembers(Long hobbyGroupId) {
        return membershipRepository.findByHobbyGroup_Id(hobbyGroupId);
    }

    public List<Membership> getParticipantsClubs(Long participantId) {
        return membershipRepository.findByParticipant_Id(participantId);
    }

    public Membership getMembership(MembershipId id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));
    }
}
