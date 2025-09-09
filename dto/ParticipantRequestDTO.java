package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantRequestDTO {

    @NotNull(message = "First name must not be null")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull(message = "Join date must not be null")
    @PastOrPresent(message = "Join date must be in the past or present")
    private LocalDateTime joinedAt;
}
