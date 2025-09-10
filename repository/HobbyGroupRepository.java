package com.example.repository;

import com.example.model.HobbyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyGroupRepository extends JpaRepository<HobbyGroup, Long> {
}
