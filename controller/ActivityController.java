package com.example.controller;

import com.example.dto.ActivityDTO;
import com.example.model.Activity;
import com.example.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tegevused")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/klubi/{clubId}")
    public ResponseEntity<ActivityDTO> createActivity(
            @PathVariable Long clubId,
            @RequestBody ActivityDTO activityDTO) {
        Activity activity = convertToEntity(activityDTO);
        Activity createdActivity = activityService.createActivity(clubId, activity);
        return new ResponseEntity<>(convertToDTO(createdActivity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> activities = activityService.getAllActivities().stream()
                .map(this::convertToDTO)
                .toList(); // Java 16+
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/klubi/{clubId}")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByClubId(@PathVariable Long clubId) {
        List<ActivityDTO> activities = activityService.getActivitiesByClubId(clubId).stream()
                .map(this::convertToDTO)
                .toList(); // Java 16+
        return ResponseEntity.ok(activities);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(
            @PathVariable Long id,
            @RequestBody ActivityDTO activityDTO) {
        Activity activity = convertToEntity(activityDTO);
        Activity updatedActivity = activityService.updateActivity(id, activity);
        return ResponseEntity.ok(convertToDTO(updatedActivity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }


    private ActivityDTO convertToDTO(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getClub().getId(),
                activity.getName(),
                activity.getOccurrenceDate()
        );
    }

    private Activity convertToEntity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setName(activityDTO.getName());
        activity.setOccurrenceDate(activityDTO.getOccurrenceDate());
        return activity;
    }
}