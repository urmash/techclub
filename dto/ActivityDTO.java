package com.example.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    private Long id;

    @NotNull(message = "Club ID must not be null")
    private Long clubId;

    @NotNull(message = "Activity name must not be null")
    @Size(min = 2, max = 50, message = "Activity name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Occurrence date must not be null")
    @Future(message = "Occurrence date must be in the future")
    private LocalDateTime occurrenceDate;
}
