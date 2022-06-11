package com.example.businessintelligence.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorAndCollaboratorDTO {

    private AuthorDTO author;

    private List<AuthorDTO> collaborators;

}
