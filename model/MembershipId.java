package com.example.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class MembershipId implements Serializable {

    private Long hobbyGroupId;
    private Long participantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembershipId that)) return false;
        return Objects.equals(hobbyGroupId, that.hobbyGroupId)
                && Objects.equals(participantId, that.participantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hobbyGroupId, participantId);
    }
}
