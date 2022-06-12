package com.example.businessintelligence.dto;

import lombok.Data;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName VenueDTO.java
 * @Description TODO
 * @createTime 2022年06月11日 22:36:00
 */
@Data
public class VenueDTO {
    private String id;
    private String title;
    private final String label="venue";

    public VenueDTO(String title) {
        this.id = title;
        this.title = title;
    }
}
