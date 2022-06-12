package com.example.businessintelligence.dto;

import lombok.Data;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName PaperAndVenueDTO.java
 * @Description TODO
 * @createTime 2022年06月11日 22:35:00
 */
@Data
public class PaperAndVenueDTO {
    private PaperDTO paperD;
    private VenueDTO venueDTO;

    public PaperAndVenueDTO(PaperDTO paperD, VenueDTO venueDTO) {
        this.paperD = paperD;
        this.venueDTO = venueDTO;
    }
}
