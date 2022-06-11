package com.example.businessintelligence.service;

import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.dao.VenueRepository;
import com.example.businessintelligence.dto.*;
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


    public WriteDTO getPapersWrittenByAuthor(String authorName) {

        Author searchAuthor = authorRepository.findAuthorByName(authorName);
        if(searchAuthor == null) {
            System.out.println("search author is null");
            return null;
        }
        AuthorDTO searchAuthorDTO = new AuthorDTO(searchAuthor);

        WriteDTO writeDTO = new WriteDTO();
        List<Paper> papers = paperRepository.findPapersWrittenByAuthorName(authorName);
        List<PaperDTO> paperDTOList = new ArrayList<>();
        for(Paper p : papers) {

            List<Venue> venues = venueRepository.findVenuesPublishPaper(p.getPaperId());
            List<PeriodicalDTO> periodicalDTOList = new ArrayList<>();
            for(Venue v : venues) {
                periodicalDTOList.add(new PeriodicalDTO(v));
            }
            PaperDTO paperDTO = new PaperDTO(p, periodicalDTOList);
            paperDTOList.add(paperDTO);

        }
        writeDTO.setAuthor(searchAuthorDTO);
        writeDTO.setPapers(paperDTOList);
        return writeDTO;

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
