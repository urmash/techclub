package com.example.controller;

import com.example.dto.HobbyGroupDTO;
import com.example.model.HobbyGroup;
import com.example.service.HobbyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hobby-groups")
@RequiredArgsConstructor
public class HobbyGroupController {

    private final HobbyGroupService hobbyGroupService;

    @PostMapping
    public ResponseEntity<HobbyGroupDTO> createHobbyGroup(@RequestBody HobbyGroupDTO dto) {
        HobbyGroup hobbyGroup = convertToEntity(dto);
        HobbyGroup created = hobbyGroupService.createHobbyGroup(hobbyGroup);
        return new ResponseEntity<>(convertToDTO(created), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HobbyGroupDTO>> getAllHobbyGroups() {
        List<HobbyGroupDTO> list = hobbyGroupService.getAllHobbyGroups().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HobbyGroupDTO> getHobbyGroupById(@PathVariable Long id) {
        HobbyGroup hobbyGroup = hobbyGroupService.getHobbyGroupById(id);
        return ResponseEntity.ok(convertToDTO(hobbyGroup));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HobbyGroupDTO> updateHobbyGroup(
            @PathVariable Long id,
            @RequestBody HobbyGroupDTO dto) {
        HobbyGroup updated = hobbyGroupService.updateHobbyGroup(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHobbyGroup(@PathVariable Long id) {
        hobbyGroupService.deleteHobbyGroup(id);
        return ResponseEntity.noContent().build();
    }

    private HobbyGroupDTO convertToDTO(HobbyGroup entity) {
        return new HobbyGroupDTO(
                entity.getId() != null ? entity.getId().longValue() : null,
                entity.getName(),
                entity.getDescription(),
                null
        );
    }

    private HobbyGroup convertToEntity(HobbyGroupDTO dto) {
        HobbyGroup hobbyGroup = new HobbyGroup();
        hobbyGroup.setName(dto.getName());
        hobbyGroup.setDescription(dto.getDescription());
        return hobbyGroup;
    }
}
