package com.example.businessintelligence.dto;

import com.example.businessintelligence.entity.node.Author;
import lombok.Data;

import java.util.List;

@Data
public class CollaborateDTO {
    private AuthorDTO author;
    private List<AuthorDTO> collaborators;

}
