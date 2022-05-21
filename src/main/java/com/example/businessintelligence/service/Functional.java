package com.example.businessintelligence.service;

import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.dao.VenueRepository;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import com.example.businessintelligence.entity.node.Venue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<Author> getAuthorCollaboratedWithAuthor(String authorId) {
        return authorRepository.findCollaborateAuthor(authorId);
    }
    public List<Venue> getVenuesPublishPaper(String paperId) {
        return venueRepository.findVenuesPublishPaper(paperId);
    }
    public List<Author> getAuthorsInSameAffiliation(String authorId) {
        return authorRepository.findAuthorsInSameAffiliation(authorId);
    }


}
