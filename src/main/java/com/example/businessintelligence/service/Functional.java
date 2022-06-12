package com.example.businessintelligence.service;

import com.example.businessintelligence.dao.*;
import com.example.businessintelligence.dto.*;
import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Affiliation;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import com.example.businessintelligence.entity.node.Venue;
import com.example.businessintelligence.entity.relation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
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
    BaseNodeRepository baseNodeRepository;
    @Resource
    BaseRelationRepository baseRelationRepository;
    @Resource
    InterestRepository interestRepository;

    public List<Author> getAuthorsByName(String authorName) {
        return authorRepository.findAuthorByName(authorName);
    }

    public AuthorAndPaperDTO getPapersWrittenByAuthor(String authorId) {

        Author searchAuthor = authorRepository.get(authorId);
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

        Author searchAuthor = authorRepository.get(authorId);
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
        Author searchAuthor = authorRepository.get(authorId);
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
        Author searchAuthor = authorRepository.get(authorId);
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

    public List<BaseNode> findAllByAuthor(String authorId) {
        return baseNodeRepository.findAllLinkedByAuthor(authorId);
    }

    public Long getUid(String nodeId, String label) {
        Long uid = 0L;
        switch (label) {
            case "author":
            case "AUTHOR":
                uid = authorRepository.get(nodeId).getId();
                break;
            case "paper":
            case "PAPER":
                uid = paperRepository.get(nodeId).getId();
                break;
            case "interest":
            case "INTEREST":
                uid = interestRepository.get(nodeId).getId();
                break;
            case "venue":
            case "VENUE":
                uid = venueRepository.get(nodeId).getId();
                break;
            case "affiliation":
            case "AFFILIATION":
                uid = affiliationRepository.get(nodeId).getId();
                break;
            default:
                break;
        }
        return uid;
    }

    public MultihopDTO MultihopBetweenNodes(Long uid1, Long uid2, int hopCount) {
        List<BaseRelation> baseRelations =  baseRelationRepository.getMultihopBetweenNodes(uid1, uid2);
        HashSet<BaseNodeDTO> baseNodeDTOHashSet = new HashSet<>(); //使用哈希集合，用于节点去重
        List<BaseRelationDTO> baseRelationDTOList = new ArrayList<>();
        for(BaseRelation baseRelation : baseRelations) {
            String nodeId1="", nodeId2="";
            String label1="", label2="";
            String source="", target="";
            String relationship="";
            if (baseRelation instanceof Collaborate) {
                nodeId1 = ((Collaborate) baseRelation).getAuthor1().getAuthorId();
                label1 = "author";
                nodeId2 = ((Collaborate) baseRelation).getAuthor2().getAuthorId();
                label2 = "author";
                source = nodeId1; target = nodeId2;
                relationship = "collaborate";
            }
            else if(baseRelation instanceof HasInterest) {
                nodeId1 = ((HasInterest) baseRelation).getAuthor().getAuthorId();
                label1 = "author";
                nodeId2 = ((HasInterest) baseRelation).getInterest().getInterestId();
                label2 = "interest";
                source = nodeId1; target = nodeId2;
                relationship = "has_interest";
            }
            else if(baseRelation instanceof Publish) {
                nodeId1 = ((Publish) baseRelation).getPaper().getPaperId();
                label1 = "paper";
                nodeId2 = ((Publish) baseRelation).getVenue().getVenueId();
                label2 = "venue";
                source = nodeId2; target = nodeId1;
                relationship = "publish";
            }
            else if(baseRelation instanceof Reference) {
                nodeId1 = ((Reference) baseRelation).getPaper1().getPaperId();
                label1 = "paper";
                nodeId2 = ((Reference) baseRelation).getPaper2().getPaperId();
                label2 = "paper";
                source = nodeId1; target = nodeId2;
                relationship = "reference";
            }
            else if(baseRelation instanceof Write) {
                nodeId1 = ((Write) baseRelation).getAuthor().getAuthorId();
                label1 = "author";
                nodeId2 = ((Write) baseRelation).getPaper().getPaperId();
                label2 = "paper";
                source = nodeId1; target = nodeId2;
                relationship = "write";
            }
            else if(baseRelation instanceof In) {
                nodeId1 = ((In) baseRelation).getAuthor().getAuthorId();
                label1 = "author";
                nodeId2 = ((In) baseRelation).getAffiliation().getAffiliationId();
                label2 = "affiliation";
                source = nodeId1; target = nodeId2;
                relationship = "in";
            }
            baseNodeDTOHashSet.add(new BaseNodeDTO(nodeId1, label1));
            baseNodeDTOHashSet.add(new BaseNodeDTO(nodeId2, label2));
            baseRelationDTOList.add(new BaseRelationDTO(source, target, relationship));
        }
        List<BaseNodeDTO> baseNodeDTOList = new ArrayList<>(baseNodeDTOHashSet);

        MultihopDTO multihopDTO = new MultihopDTO();
        multihopDTO.setNodes(baseNodeDTOList);
        multihopDTO.setLinks(baseRelationDTOList);

        return multihopDTO;
    }

}
