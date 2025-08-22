package com.example.repository;

import com.example.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByClubId(Long clubId);
    Optional<Activity> findById(Long id); // Lisatud
}