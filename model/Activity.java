package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "activity") // skeemis on väiketähtedega
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "hobby_group_id", nullable = false)
    private HobbyGroup hobbyGroup;

    @Size(max = 50)
    @NotNull
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull
    @Future(message = "Scheduled date must be in the future")
    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;
}
