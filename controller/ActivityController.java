package com.example.controller;

import com.example.dto.ActivityDTO;
import com.example.model.Activity;
import com.example.model.HobbyGroup;
import com.example.service.ActivityService;
import com.example.service.HobbyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final HobbyGroupService hobbyGroupService;

    @GetMapping("/club/{clubId}")
    public List<ActivityDTO> getByClubId(@PathVariable Long clubId) {
        return activityService.getActivitiesByHobbyGroupId(clubId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ActivityDTO createActivity(@RequestBody ActivityDTO activityDTO) {
        Activity activity = convertToEntity(activityDTO);
        Activity saved = activityService.saveActivity(activity);
        return convertToDTO(saved);
    }

    @PutMapping("/{id}")
    public ActivityDTO updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        Activity existing = activityService.getActivityById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));

        existing.setName(activityDTO.getName());
        existing.setScheduledAt(activityDTO.getOccurrenceDate());

        if (activityDTO.getClubId() != null) {
            HobbyGroup hobbyGroup = hobbyGroupService.getHobbyGroupById(activityDTO.getClubId());
            existing.setHobbyGroup(hobbyGroup);
        }

        Activity updated = activityService.saveActivity(existing);
        return convertToDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }

    private ActivityDTO convertToDTO(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getHobbyGroup() != null ? activity.getHobbyGroup().getId().longValue() : null,
                activity.getName(),
                activity.getScheduledAt()
        );
    }

    private Activity convertToEntity(ActivityDTO dto) {
        Activity activity = new Activity();
        activity.setName(dto.getName());
        activity.setScheduledAt(dto.getOccurrenceDate());

        if (dto.getClubId() != null) {
            HobbyGroup hobbyGroup = hobbyGroupService.getHobbyGroupById(dto.getClubId());
            activity.setHobbyGroup(hobbyGroup);
        }

        return activity;
    }
}
