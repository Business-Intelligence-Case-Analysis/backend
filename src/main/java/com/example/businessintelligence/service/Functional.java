package com.example.businessintelligence.service;

import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.dao.VenueRepository;
import com.example.businessintelligence.dto.AuthorDTO;
import com.example.businessintelligence.dto.CollaborateDTO;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import com.example.businessintelligence.entity.node.Venue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class Functional {
    @Resource
    PaperRepository paperRepository;
    @Resource
    AuthorRepository authorRepository;
    @Resource
    VenueRepository venueRepository;


    public List<Paper> getPapersWrittenByAuthor(String authorId) {
        return paperRepository.findPapersWrittenByAuthor(authorId);
    }
    public CollaborateDTO getAuthorCollaboratedWithAuthor(String authorName) {

        Author searchAuthor = authorRepository.findAuthorByName(authorName);
        if(searchAuthor == null) {
            System.out.println("search author is null");
            return null;
        }

        AuthorDTO searchAuthorDTO = new AuthorDTO(searchAuthor);

        List<Author> authors = authorRepository.findCollaborateAuthorByName(authorName);
        List<AuthorDTO> authorDTOs = new ArrayList<>();

        CollaborateDTO collaborateDTO = new CollaborateDTO();
        collaborateDTO.setAuthor(searchAuthorDTO);
        for(Author au : authors) {
            AuthorDTO authorDTO = new AuthorDTO(au);
            authorDTOs.add(authorDTO);
        }

        collaborateDTO.setAuthor(searchAuthorDTO);
        collaborateDTO.setCollaborators(authorDTOs);

        return collaborateDTO;
    }
    public List<Venue> getVenuesPublishPaper(String paperId) {
        return venueRepository.findVenuesPublishPaper(paperId);
    }
    public List<Author> getAuthorsInSameAffiliation(String authorId) {
        return authorRepository.findAuthorsInSameAffiliation(authorId);
    }


}
