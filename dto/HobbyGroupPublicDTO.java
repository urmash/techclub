package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Avalikus vaates nähtav klubi/huvigrupi info.
 * Ei sisalda liikmete nimesid ega muud tundlikku infot.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HobbyGroupPublicDTO {

    private Long id;             // Klubi ID
    private String name;         // Nähtav nimi (nt "Pilliring")
    private String description;  // Lühike tutvustus
    private String meetingTime;  // nt "Esmaspäeviti kell 17:00"
}
