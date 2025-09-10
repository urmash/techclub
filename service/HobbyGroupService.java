package com.example.service;

import com.example.model.HobbyGroup;
import com.example.repository.HobbyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyGroupService {

    private final HobbyGroupRepository hobbyGroupRepository;

    public HobbyGroup createHobbyGroup(HobbyGroup hobbyGroup) {
        return hobbyGroupRepository.save(hobbyGroup);
    }

    public List<HobbyGroup> getAllHobbyGroups() {
        return hobbyGroupRepository.findAll();
    }

    public HobbyGroup getHobbyGroupById(Long id) {
        return hobbyGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HobbyGroup not found with id: " + id));
    }

    public HobbyGroup updateHobbyGroup(Long id, HobbyGroup hobbyGroup) {
        HobbyGroup existing = getHobbyGroupById(id);
        existing.setName(hobbyGroup.getName());
        existing.setDescription(hobbyGroup.getDescription());
        return hobbyGroupRepository.save(existing);
    }

    public void deleteHobbyGroup(Long id) {
        hobbyGroupRepository.deleteById(id);
    }
}
