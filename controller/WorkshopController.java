package com.example.controller;

import com.example.dto.WorkshopDTO;
import com.example.model.Workshop;
import com.example.service.WorkshopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workshops")
public class WorkshopController {

    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @GetMapping
    public ResponseEntity<List<WorkshopDTO>> getAllWorkshops() {
        List<WorkshopDTO> workshops = workshopService.getAllWorkshops().stream()
                .map(this::convertToDTO)
                .toList(); // ✅ Java 16+ toList()
        return ResponseEntity.ok(workshops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkshopDTO> getWorkshopById(@PathVariable Long id) {
        Workshop workshop = workshopService.getWorkshopById(id);
        return ResponseEntity.ok(convertToDTO(workshop));
    }

    @PostMapping
    public ResponseEntity<WorkshopDTO> createWorkshop(@RequestBody WorkshopDTO workshopDTO) {
        Workshop workshop = convertToEntity(workshopDTO);
        Workshop saved = workshopService.saveWorkshop(workshop);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long id) {
        workshopService.deleteWorkshop(id);
        return ResponseEntity.noContent().build();
    }

    // --- DTO konverteerimine ---
    private WorkshopDTO convertToDTO(Workshop workshop) {
        return new WorkshopDTO(
                workshop.getId(),
                workshop.getTitle(),
                workshop.getDescription(),
                workshop.getDate()
        );
    }

    private Workshop convertToEntity(WorkshopDTO dto) {
        Workshop workshop = new Workshop();
        workshop.setTitle(dto.getTitle());
        workshop.setDescription(dto.getDescription());
        workshop.setDate(dto.getDate());
        return workshop;
    }
}
