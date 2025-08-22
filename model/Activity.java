package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tegevus")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "huviring_id", nullable = false)
    private Club club;

    @Column(name = "nimi", nullable = false, length = 50)
    private String name;

    @Column(name = "toimumise_kp", nullable = false)
    private LocalDateTime occurrenceDate;

    // --- Getters/Setters ---
    public Long getId() { return id; }
    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDateTime getOccurrenceDate() { return occurrenceDate; }
    public void setOccurrenceDate(LocalDateTime occurrenceDate) { this.occurrenceDate = occurrenceDate; }
}