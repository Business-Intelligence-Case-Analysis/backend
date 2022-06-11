package com.example.businessintelligence.dto;

import lombok.Data;

import java.util.List;

@Data
public class WriteDTO {
    private AuthorDTO author;
    private List<PaperDTO> papers;

}
