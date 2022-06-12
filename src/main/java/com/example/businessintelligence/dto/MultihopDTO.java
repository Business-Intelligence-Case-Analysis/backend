package com.example.businessintelligence.dto;

import lombok.Data;

import java.util.List;

@Data
public class MultihopDTO {

    List<BaseNodeDTO> nodes;

    List<BaseRelationDTO> links;
}
