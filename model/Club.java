package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "huviring") // Parandatud tabeli nimi
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nimi", nullable = false, length = 50) // Parandatud veeru nimi
    private String name; // Muudetud inglise keelseks

    @Column(name = "kirjeldus", length = 255)
    private String description;

    @Column(name = "loodud_kp", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Automaatne kuupäev

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities = new ArrayList<>(); // Muudetud seos tegevustega

    // --- Constructors ---
    public Club() {}

    public Club(String name) {
        this.name = name;
    }

    // --- Getters/Setters ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Activity> getActivities() { return activities; }
}