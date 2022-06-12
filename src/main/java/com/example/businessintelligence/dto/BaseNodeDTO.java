package com.example.businessintelligence.dto;

import lombok.Data;

@Data
public class BaseNodeDTO {

    String nodeId;

    String label;

    public BaseNodeDTO(String nodeId, String label) {
        this.nodeId = nodeId;
        this.label = label;
    }
}
