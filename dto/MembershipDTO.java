package com.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {

    private Long clubId;
    private Long participantId;
    private LocalDateTime joinedAt;
}
