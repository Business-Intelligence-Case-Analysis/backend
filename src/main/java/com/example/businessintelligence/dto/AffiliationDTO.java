package com.example.businessintelligence.dto;

import com.example.businessintelligence.entity.node.Affiliation;
import lombok.Data;

import java.util.List;

@Data
public class AffiliationDTO {

    String name;

    String label;

    List<AuthorDTO> authorDTOList;

    public void setAffiliationAndAuthors(Affiliation affiliation, List<AuthorDTO> authorDTOList) {
        this.name = affiliation.getAffiliationId();
        this.label = "affiliation";
        this.authorDTOList = authorDTOList;
    }
}
