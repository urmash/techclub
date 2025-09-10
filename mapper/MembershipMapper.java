package com.example.mapper;

import com.example.dto.MembershipResponseDTO;
import com.example.model.Membership;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MembershipMapper {

    // Ühtne eestikeelne kuupäeva‑ ja kellaajavorming
    private static final DateTimeFormatter EE_VORMING =
            DateTimeFormatter.ofPattern("d. MMMM yyyy, HH:mm", new Locale("et"));

    /**
     * Teisendab Membership entiteedi MembershipResponseDTO‑ks.
     *
     * @param membership Membership entiteet
     * @return MembershipResponseDTO, kus kuupäev vormindatud eesti keeles
     */
    public static MembershipResponseDTO toDTO(Membership membership) {
        if (membership == null) {
            return null;
        }

        return new MembershipResponseDTO(
                membership.getId(),                                // MembershipId komposiitvõti
                membership.getParticipant().getFullName(),         // liikme nimi
                membership.getHobbyGroup().getName(),              // grupi/klubi nimi
                membership.getJoinedAt().format(EE_VORMING)        // kuupäev + kellaaeg eesti keeles
        );
    }
}
