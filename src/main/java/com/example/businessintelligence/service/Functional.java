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
import com.alibaba.fastjson2.JSONReader;
import com.example.businessintelligence.dao.AffiliationRepository;
import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.dao.VenueRepository;
import com.example.businessintelligence.dao.mysqlDao.PaperRepositoryMysql;
import com.example.businessintelligence.dto.*;
import com.example.businessintelligence.entity.mysqlEntity.PaperMysql;
import com.example.businessintelligence.entity.node.*;
import com.example.businessintelligence.entity.relation.In;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

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
    @Resource
    PaperRepositoryMysql paperRepositoryMysql;


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

    public List<AuthorDTO> getAuthorDTOs(List<Author> authors){
        ArrayList<AuthorDTO> authorDTOS = new ArrayList<>();

        for(Author reAuthor:authors){
            authorDTOS.add(new AuthorDTO(reAuthor));
        }
        return authorDTOS;
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
        List<BaseRelation> baseRelations =  baseRelationRepository.getMultihopBetweenNodes(uid1, uid2, hopCount);
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
    public List<AuthorDTO> getInfluentialAuthors(String interest,String indicator) {
            List<Author> authors = authorRepository.findAuthorsByInterest(interest);

            if (authors.size() == 0) {
                System.out.println("this interest has no author!");
                return null;
            }
            boolean flag = true;


            List<AuthorDTO> authorDTOs = getAuthorDTOs(authors);

            try {
                authorDTOs.sort(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if(o1 instanceof AuthorDTO && o2 instanceof AuthorDTO){
                            AuthorDTO a1 = (AuthorDTO) o1;
                            AuthorDTO a2 = (AuthorDTO) o2;
                            Class<AuthorDTO> authorDTOClass = AuthorDTO.class;
                            Object a1F = null;
                            Object a2F = null;
                            try {
                                Field declaredField = authorDTOClass.getDeclaredField(indicator);
                                declaredField.setAccessible(true);
                                a1F = declaredField.get(a1);
                                a2F = declaredField.get(a2);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                                System.out.println("this indicator does not exists");
                            }
                            if (a1F instanceof Double && a2F instanceof Double){
                                Double a1FD = (Double) a1F;
                                Double a2FD = (Double) a2F;
                                return -Double.compare(a1FD, a2FD);
                            } else{
                                throw new RuntimeException("the indicator type is not right");
                            }
                        }
                        else{
                            throw new RuntimeException("the class type is not right");
                        }
                    }
                });
            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
            if (!flag)
                return null;
            else {
                int size = authorDTOs.size();
                return size>=5?authorDTOs.subList(0,5):authorDTOs.subList(0,size);
            }
        }
}
