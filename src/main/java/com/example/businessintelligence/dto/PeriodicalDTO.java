package com.example.businessintelligence.dto;

import com.example.businessintelligence.entity.node.Venue;
import lombok.Data;

@Data
public class PeriodicalDTO {

    String name;

    String label;

    public PeriodicalDTO(Venue venue) {
        this.name = venue.getVenueId();
        this.label = "periodical";
    }

}
