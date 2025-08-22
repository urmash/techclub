package com.example.service;

import com.example.model.Workshop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopService {
    public List<Workshop> getAllWorkshops() {
        return List.of(); // Placeholder, will be replaced with repository call
    }

    public Workshop getWorkshopById(Long id) {
        return null;
    }

    public Workshop saveWorkshop(Workshop workshop) {
        return workshop;
    }

    public void deleteWorkshop(Long id) {
        Workshop workshop = getWorkshopById(id);
        workshopRepository.delete(workshop);
    }
    }
}
