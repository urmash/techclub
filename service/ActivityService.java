package com.example.service;

import com.example.model.Activity;
import com.example.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public Activity createActivity(Long hobbyGroupId, Activity activity) {
        // vajadusel seome HobbyGroup siit
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByHobbyGroupId(Long hobbyGroupId) {
        return activityRepository.findByHobbyGroupId(hobbyGroupId);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tegevus ei leitud id-ga: " + id));

        existingActivity.setName(updatedActivity.getName());
        existingActivity.setScheduledAt(updatedActivity.getScheduledAt());

        return activityRepository.save(existingActivity);
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }
}
