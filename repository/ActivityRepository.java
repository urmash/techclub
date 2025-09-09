package com.example.repository;

import com.example.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // meetodinimi peab vastama Activity klassi väljanimele
    List<Activity> findByHobbyGroupId(Long hobbyGroupId);

    @NonNull
    Optional<Activity> findById(@NonNull Long id);
}
