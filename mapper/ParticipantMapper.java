package com.example.mapper;

import com.example.dto.ParticipantRequestDTO;
import com.example.dto.ParticipantResponseDTO;
import com.example.model.Participant;

public final class ParticipantMapper {

    // Private constructor to prevent instantiation
    private ParticipantMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Participant toEntity(ParticipantRequestDTO dto) {
        Participant participant = new Participant();
        participant.setFirstName(dto.getFirstName());
        participant.setLastName(dto.getLastName());
        participant.setJoinedAt(dto.getJoinedAt());
        return participant;
    }

    public static ParticipantResponseDTO toDTO(Participant participant) {
        return new ParticipantResponseDTO(
                participant.getId(),
                participant.getFirstName(),
                participant.getLastName(),
                participant.getJoinedAt()
        );
    }
}
