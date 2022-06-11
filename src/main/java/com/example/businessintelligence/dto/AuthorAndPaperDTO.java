package com.example.businessintelligence.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorAndPaperDTO {

    private AuthorDTO author;

    private List<PaperDTO> papers;

}
