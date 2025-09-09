package com.example.service;

import com.example.model.Workshop;
import com.example.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public Workshop saveWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop); // ✅ salvestab andmebaasi
    }

    public void deleteWorkshop(Long id) {
        Workshop workshop = getWorkshopById(id);
        workshopRepository.delete(workshop); // ✅ kustutab olemasoleva
    }

    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll(); // ✅ tagastab kõik workshopid
    }

    public Workshop getWorkshopById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workshop not found with id: " + id)); // ✅ turvaline otsing
    }
}
