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

    public List<Author> getAuthorsByName(String authorName) {
        return authorRepository.findAuthorByName(authorName);
    }

    public AuthorAndPaperDTO getPapersWrittenByAuthor(String authorId) {

        Author searchAuthor = authorRepository.findAuthorByAuthorId(authorId);
        if(searchAuthor == null) {
            System.out.println("search author is null");
            return null;
        }
        AuthorDTO searchAuthorDTO = new AuthorDTO(searchAuthor);

        AuthorAndPaperDTO authorAndPaperDTO = new AuthorAndPaperDTO();
        List<Paper> papers = paperRepository.findPapersWrittenByAuthorId(authorId);
        List<PaperDTO> paperDTOList = new ArrayList<>();
        for(Paper p : papers) {

            List<Venue> venues = venueRepository.findVenuesPublishPaper(p.getPaperId());
            List<PeriodicalDTO> periodicalDTOList = new ArrayList<>();
            for(Venue v : venues) {
                periodicalDTOList.add(new PeriodicalDTO(v));
            }
            PaperDTO paperDTO = new PaperDTO();
            paperDTO.setPaperAndPeriodical(p, periodicalDTOList);
            paperDTOList.add(paperDTO);

        }
        authorAndPaperDTO.setAuthor(searchAuthorDTO);
        authorAndPaperDTO.setPapers(paperDTOList);
        return authorAndPaperDTO;

    }
    public CollaborateDTO getAuthorCollaboratedWithAuthor(String authorId) {

        Author searchAuthor = authorRepository.findAuthorByAuthorId(authorId);
        if(searchAuthor == null) {
            System.out.println("search author is null");
            return null;
        }

        AuthorDTO searchAuthorDTO = new AuthorDTO(searchAuthor);

        List<Author> authors = authorRepository.findCollaborateAuthorById(authorId);
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

    public AuthorAndPaperDTO getPapersCitedByAuthor(String authorId) {
        Author searchAuthor = authorRepository.findAuthorByAuthorId(authorId);
        if(searchAuthor == null) {
            System.out.println("search author is null");
            return null;
        }
        AuthorDTO searchAuthorDTO = new AuthorDTO(searchAuthor);

        AuthorAndPaperDTO authorAndPaperDTO = new AuthorAndPaperDTO();
        List<Paper> papers = paperRepository.findPapersCitedByAuthor(searchAuthor.getAuthorId());
        List<PaperDTO> paperDTOList = new ArrayList<>();
        for(Paper p : papers) {
            List<Author> authors = authorRepository.findAuthorWritingPaper(p.getPaperId());
            List<AuthorDTO> authorDTOList = new ArrayList<>();
            for(Author au : authors) {
                authorDTOList.add(new AuthorDTO(au));
            }
            PaperDTO paperDTO = new PaperDTO();
            paperDTO.setPaperAndAuthor(p, authorDTOList);
        }
        authorAndPaperDTO.setAuthor(searchAuthorDTO);
        authorAndPaperDTO.setPapers(paperDTOList);
        return authorAndPaperDTO;
    }

    public List<Venue> getVenuesPublishPaper(String paperId) {
        return venueRepository.findVenuesPublishPaper(paperId);
    }
    public List<Author> getAuthorsInSameAffiliation(String authorId) {
        return authorRepository.findAuthorsInSameAffiliation(authorId);
    }


}
