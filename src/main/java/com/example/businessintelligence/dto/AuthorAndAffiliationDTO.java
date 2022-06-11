package com.example.businessintelligence.dto;


import lombok.Data;

import java.util.List;

@Data
public class AuthorAndAffiliationDTO {

    private AuthorDTO author;

    private List<AffiliationDTO> affiliation;

}
