package com.example.service;

import com.example.model.Club;
import com.example.model.Membership;
import com.example.model.Participant;
import com.example.repository.ClubRepository;
import com.example.repository.MembershipRepository;
import com.example.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final ClubRepository clubRepository;
    private final ParticipantRepository participantRepository;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository,
                             ClubRepository clubRepository,
                             ParticipantRepository participantRepository) {
        this.membershipRepository = membershipRepository;
        this.clubRepository = clubRepository;
        this.participantRepository = participantRepository;
    }

    // Osaleja klubiga liitmine
    @Transactional
    public Membership joinClub(Long clubId, Long participantId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Klubi ei leitud id-ga: " + clubId));

        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Osaleja ei leitud id-ga: " + participantId));

        Membership membership = new Membership();
        membership.setClub(club);
        membership.setParticipant(participant);

        return membershipRepository.save(membership);
    }

    @Transactional
    public void leaveClub(Long clubId, Long participantId) {
        membershipRepository.deleteByClubIdAndParticipantId(clubId, participantId);
    }

    public List<Membership> getClubMembers(Long clubId) {
        return membershipRepository.findByClubId(clubId);
    }

    public List<Membership> getParticipantsClubs(Long participantId) {
        return membershipRepository.findByParticipantId(participantId);
    }
}