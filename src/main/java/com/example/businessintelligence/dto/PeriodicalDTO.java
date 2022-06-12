package com.example.businessintelligence.dto;

import com.example.businessintelligence.entity.node.Venue;
import lombok.Data;

@Data
public class PeriodicalDTO {

    String name;

    String label;

    public PeriodicalDTO() {
    }

    public PeriodicalDTO(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public PeriodicalDTO(Venue venue) {
        this.name = venue.getVenueId();
        this.label = "periodical";
    }

}
