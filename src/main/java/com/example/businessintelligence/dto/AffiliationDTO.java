package com.example.businessintelligence.dto;

import com.example.businessintelligence.entity.node.Affiliation;
import lombok.Data;

import java.util.List;

@Data
public class AffiliationDTO {
    int id;

    String name;

    String label;

    List<AuthorDTO> authorDTOList;

    public AffiliationDTO(Affiliation affiliation, List<AuthorDTO> authorDTOList) {
        this.id = 0;
        this.name = affiliation.getAffiliationId();
        this.label = "affiliation";
        this.authorDTOList = authorDTOList;
    }
}
