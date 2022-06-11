package com.example.businessintelligence.service;

import com.alibaba.fastjson2.JSONReader;
import com.example.businessintelligence.dao.AffiliationRepository;
import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.dao.VenueRepository;
import com.example.businessintelligence.dto.*;
import com.example.businessintelligence.entity.node.Affiliation;
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
    @Resource
    AffiliationRepository affiliationRepository;

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
    public AuthorAndCollaboratorDTO getAuthorCollaboratedWithAuthor(String authorId) {

        Author searchAuthor = authorRepository.findAuthorByAuthorId(authorId);
        if(searchAuthor == null) {
            System.out.println("search author is null");
            return null;
        }

        AuthorDTO searchAuthorDTO = new AuthorDTO(searchAuthor);

        List<Author> authors = authorRepository.findCollaborateAuthorById(authorId);
        List<AuthorDTO> authorDTOs = new ArrayList<>();

        AuthorAndCollaboratorDTO authorAndCollaboratorDTO = new AuthorAndCollaboratorDTO();
        authorAndCollaboratorDTO.setAuthor(searchAuthorDTO);
        for(Author au : authors) {
            AuthorDTO authorDTO = new AuthorDTO(au);
            authorDTOs.add(authorDTO);
        }

        authorAndCollaboratorDTO.setAuthor(searchAuthorDTO);
        authorAndCollaboratorDTO.setCollaborators(authorDTOs);

        return authorAndCollaboratorDTO;
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
            paperDTOList.add(paperDTO);
        }
        authorAndPaperDTO.setAuthor(searchAuthorDTO);
        authorAndPaperDTO.setPapers(paperDTOList);
        return authorAndPaperDTO;
    }

    public List<Venue> getVenuesPublishPaper(String paperId) {
        return venueRepository.findVenuesPublishPaper(paperId);
    }

    public AuthorAndAffiliationDTO getAuthorsInSameAffiliation(String authorId) {
        Author searchAuthor = authorRepository.findAuthorByAuthorId(authorId);
        if(searchAuthor == null) {
            System.out.println("search author is null");
            return null;
        }
        AuthorDTO searchAuthorDTO = new AuthorDTO(searchAuthor);

        List<Affiliation> affiliationList = affiliationRepository.findAffiliationsByAuthorId(authorId);
        AuthorAndAffiliationDTO authorAndAffiliationDTO = new AuthorAndAffiliationDTO();
        List<AffiliationDTO> affiliationDTOList = new ArrayList<>();
        for(Affiliation aff : affiliationList) {
            List<Author> authors = authorRepository.findAuthorByAffiliation(aff.getAffiliationId());
            List<AuthorDTO> authorDTOList = new ArrayList<>();
            for(Author au : authors) {
                authorDTOList.add(new AuthorDTO(au));
            }
            AffiliationDTO affiliationDTO = new AffiliationDTO();
            affiliationDTO.setAffiliationAndAuthors(aff, authorDTOList);
            affiliationDTOList.add(affiliationDTO);
        }
        authorAndAffiliationDTO.setAuthor(searchAuthorDTO);
        authorAndAffiliationDTO.setAffiliation(affiliationDTOList);

        return authorAndAffiliationDTO;
    }

    public List<PaperDTO> getPapersPublishedByVenueInYear(String venue, String year) {
        List<Paper> papers = paperRepository.findPapersPublishedByVenueAndYear(venue, year);
        List<PaperDTO> paperDTOList = new ArrayList<>();
        for(Paper p : papers) {
            PaperDTO paperDTO = new PaperDTO();
            paperDTO.setPaper(p);
            paperDTOList.add(paperDTO);
        }
        return paperDTOList;
    }

    public List<AuthorDTO> getAuthorsByVenue(String venue) {
        List<Author> authors = authorRepository.findAuthorsByVenue(venue);
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for(Author au : authors) {
            authorDTOList.add(new AuthorDTO(au));
        }
        return authorDTOList;
    }

    public int getPaperIsCitedTimes(String paperId) {
        return paperRepository.getPaperIdCitedTimes(paperId);
    }

    public int getPapersInVenueIsCitedTimes(String venueId) {
        return venueRepository.getPapersInVenueIsCitedTimes(venueId);
    }



}
