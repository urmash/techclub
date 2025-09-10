package com.example.mapper;

import com.example.dto.HobbyGroupPublicDTO;
import com.example.model.HobbyGroup;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HobbyGroupMapper {

    private static final DateTimeFormatter EE_TIME =
            DateTimeFormatter.ofPattern("d. MMMM yyyy, HH:mm", new Locale.Builder().setLanguage("et").build());

    public static HobbyGroupPublicDTO toPublicDTO(HobbyGroup group) {
        if (group == null) {
            return null;
        }

        String meetingTimeStr = null;
        if (group.getMeetingTime() != null) {
            meetingTimeStr = group.getMeetingTime().format(EE_TIME);
        }

        return new HobbyGroupPublicDTO(
                group.getId(),
                group.getName(),
                group.getDescription(),
                meetingTimeStr
        );
    }

}
