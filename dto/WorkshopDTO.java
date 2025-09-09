package com.example.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopDTO {

    private Long id;

    @NotNull(message = "Workshop title must not be null")
    @Size(min = 2, max = 100, message = "Workshop title must be between 2 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Workshop date must not be null")
    @Future(message = "Workshop date must be in the future")
    private LocalDate date;
}
