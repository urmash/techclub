package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Club;
import com.example.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Transactional
    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<Club> getClubById(Long id) {
        return clubRepository.findById(id);
    }

    @Transactional
    public Club updateClub(Long id, Club updatedClub) {
        Club existingClub = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Klubi ei leitud id-ga: " + id));

        existingClub.setName(updatedClub.getName());
        existingClub.setDescription(updatedClub.getDescription());
        return clubRepository.save(existingClub);
    }

    @Transactional
    public void deleteClub(Long id) {
        if (!clubRepository.existsById(id)) {
            throw new ResourceNotFoundException("Klubi ei leitud id-ga: " + id);
        }
        clubRepository.deleteById(id);
    }
}