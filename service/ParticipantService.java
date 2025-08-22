package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Participant;
import com.example.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Transactional
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    @Transactional
    public Participant updateParticipant(Long id, Participant updatedParticipant) {
        Participant existingParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));

        existingParticipant.setFirstName(updatedParticipant.getFirstName());
        existingParticipant.setLastName(updatedParticipant.getLastName());
        existingParticipant.setJoinedAt(updatedParticipant.getJoinedAt());

        return participantRepository.save(existingParticipant);
    }

    @Transactional
    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participant not found with id: " + id);
        }
        participantRepository.deleteById(id);
    }
}
