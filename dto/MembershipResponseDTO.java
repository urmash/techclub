package com.example.dto;

import com.example.model.MembershipId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponseDTO {
    private MembershipId id;
    private String memberName;
    private String membershipType;
    private String joinedAt;
}
