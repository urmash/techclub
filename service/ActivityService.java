package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Activity;
import com.example.model.Club;
import com.example.repository.ActivityRepository;
import com.example.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository,
                           ClubRepository clubRepository) {
        this.activityRepository = activityRepository;
        this.clubRepository = clubRepository;
    }

    @Transactional
    public Activity createActivity(Long clubId, Activity activity) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("Klubi ei leitud id-ga: " + clubId));
        activity.setClub(club);
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByClubId(Long clubId) {
        return activityRepository.findByClubId(clubId);
    }

    @Transactional
    public Activity updateActivity(Long id, Activity updatedActivity) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tegevus ei leitud id-ga: " + id));

        existingActivity.setName(updatedActivity.getName());
        existingActivity.setOccurrenceDate(updatedActivity.getOccurrenceDate());
        return activityRepository.save(existingActivity);
    }

    @Transactional
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tegevus ei leitud id-ga: " + id);
        }
        activityRepository.deleteById(id);
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }
}
