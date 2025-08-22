package com.example.repository;

import com.example.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {}
