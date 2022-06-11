package com.example.businessintelligence.service;

import com.alibaba.fastjson2.JSONReader;
import com.example.businessintelligence.dao.AffiliationRepository;
import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.dao.VenueRepository;
import com.example.businessintelligence.dao.mysqlDao.PaperRepositoryMysql;
import com.example.businessintelligence.dto.*;
import com.example.businessintelligence.entity.mysqlEntity.PaperMysql;
import com.example.businessintelligence.entity.node.Affiliation;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import com.example.businessintelligence.entity.node.Venue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Resource
    PaperRepositoryMysql paperRepositoryMysql;

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
                if(!Objects.equals(au.getAuthorId(), authorId))
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

    public List<AuthorDTO> findAuthorsInAffiliation(String affiliationId) {
        List<Author> authors = authorRepository.findAuthorByAffiliation(affiliationId);
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for(Author au : authors) {
            authorDTOList.add(new AuthorDTO(au));
        }
        return authorDTOList;
    }

    public List<AuthorDTO> findAuthorsByInterest(String interest) {
        List<Author> authors = authorRepository.findAuthorsByInterest(interest);
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for(Author au : authors) {
            authorDTOList.add(new AuthorDTO(au));
        }
        return authorDTOList;
    }
    public List<PaperMysql> getPapersByPaperTitle(String paperTitle){
        return paperRepositoryMysql.findPaperMysqlByTitle(paperTitle);
    }

    public PaperReferencesDTO getPaperAndReferences(String paperId){
        PaperDTO paperDTO = getPaperDTO(paperId);

        if (paperDTO == null)
            return null;;

        List<Paper> references = paperRepository.findPapersByReference(paperId);
        List<PaperDTO> referencesDTO = new ArrayList<PaperDTO>();

        // 遍历每一篇被引用的文章，封装成对应的DTO
        for(Paper reference:references){
            List<Author> reAuthors = authorRepository.findAuthorWritingPaper(reference.getPaperId());
            ArrayList<AuthorDTO> reAuthorDTOS = new ArrayList<>();

            for(Author reAuthor:reAuthors){
                reAuthorDTOS.add(new AuthorDTO(reAuthor));
            }

            PaperDTO rePaper = new PaperDTO();
            rePaper.setPaperAndAuthor(reference,reAuthorDTOS);

            referencesDTO.add(rePaper);
        }

        return new PaperReferencesDTO(paperDTO, referencesDTO);
    }

    public PaperAndVenueDTO getPaperAndVenueByPaperId(String paperId){
        PaperDTO paperDTO = getPaperDTO(paperId);

        if (paperDTO == null)
            return null;

        Venue venue = venueRepository.findVenueByPublishPaperId(paperId);
        VenueDTO venueDTO = new VenueDTO(venue.getVenueId());

        return new PaperAndVenueDTO(paperDTO,venueDTO);
    }

    public PaperDTO getPaperDTO(String paperId){

        PaperMysql paperMysql = paperRepositoryMysql.findPaperMysqlByPaperId(Integer.parseInt(paperId));

        if (paperMysql == null){
            System.out.println("this paperId is not right!");
            return null;
        }

        List<Author> authors = authorRepository.findAuthorWritingPaper(paperId);
        ArrayList<AuthorDTO> authorDTOS = new ArrayList<>();
        // 封装到对应的作者的DTO
        for(Author author : authors){
            authorDTOS.add(new AuthorDTO(author));
        }
        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setPaperMysqlAndAuthor(paperMysql,authorDTOS);
        return paperDTO;
    }
}
