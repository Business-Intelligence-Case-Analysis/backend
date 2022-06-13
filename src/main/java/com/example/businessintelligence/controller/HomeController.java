package com.example.businessintelligence.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.businessintelligence.dto.*;
import com.example.businessintelligence.entity.logicalEntity.ApiResult;
import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.mysqlEntity.PaperMysql;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.service.CacheService;
import com.example.businessintelligence.service.Functional;
import com.example.businessintelligence.utils.ApiResultHandler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.rowset.CachedRowSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
public class HomeController {
    @Resource
    Functional functional;
    @Resource
    CacheService cacheService;

    @PostMapping("/getAuthorsByName")
    public ApiResult getAuthorsByName(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("author");
        String key = "getAuthorsByName" + name;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, Author.class));
        }
        List<Author> authorList = functional.getAuthorsByName(name);
        if(authorList.size() == 0) {
            return ApiResultHandler.empty();
        }
        cacheService.addListCache(key,authorList,24, TimeUnit.HOURS);
        return ApiResultHandler.success(authorList);
    }

    @PostMapping("/getPapersAndVenues")
    public ApiResult getPapersAndVenues(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        String key = "getPapersAndVenues"+authorId;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getObject(key, AuthorAndPaperDTO.class));
        }
        AuthorAndPaperDTO authorAndPaperDTO = functional.getPapersWrittenByAuthor(authorId);
        if(authorAndPaperDTO == null) {
            return ApiResultHandler.empty();
        }
        cacheService.add(key,authorAndPaperDTO,24,TimeUnit.HOURS);
        return ApiResultHandler.success(authorAndPaperDTO);
    }

    @PostMapping("/getCollaborateAuthors")
    public ApiResult getCollaborateAuthors(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        String key = "getCollaborateAuthors"+authorId;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getObject(key, AuthorAndCollaboratorDTO.class));
        }
        AuthorAndCollaboratorDTO authorAndCollaboratorDTO = functional.getAuthorCollaboratedWithAuthor(authorId);
        if(authorAndCollaboratorDTO == null) {
            return ApiResultHandler.empty();
        }
        cacheService.add(key,authorAndCollaboratorDTO,24,TimeUnit.HOURS);
        return ApiResultHandler.success(authorAndCollaboratorDTO);
    }

        @PostMapping("/getPapersAndAuthorsCitedByAuthor")
    public ApiResult getPapersAndAuthorsCitedByAuthor(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        String key = "getPapersAndAuthorsCitedByAuthor"+authorId;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getObject(key, AuthorAndPaperDTO.class));
        }
        AuthorAndPaperDTO authorAndPaperDTO = functional.getPapersCitedByAuthor(authorId);
        if(authorAndPaperDTO == null) {
            return ApiResultHandler.empty();
        }
        cacheService.add(key,authorAndPaperDTO,24,TimeUnit.HOURS);
        return ApiResultHandler.success(authorAndPaperDTO);
    }

    @PostMapping("/getAuthorsInSameAffiliation")
    public ApiResult getAuthorsInSameAffiliation(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        String key = "getAuthorsInSameAffiliation"+authorId;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getObject(key, AuthorAndAffiliationDTO.class));
        }
        AuthorAndAffiliationDTO authorAndAffiliationDTO = functional.getAuthorsInSameAffiliation(authorId);
        if(authorAndAffiliationDTO == null) {
            return ApiResultHandler.empty();
        }
        cacheService.add(key,authorAndAffiliationDTO,24,TimeUnit.HOURS);
        return ApiResultHandler.success(authorAndAffiliationDTO);
    }

    @PostMapping("/getPapersPublishedByVenueInYear")
    public ApiResult getPapersPublishedByVenueInYear(@RequestBody JSONObject jsonObject) {
        String venue = jsonObject.getString("venue");
        String year = jsonObject.getInteger("year").toString();
        String key = "getPapersPublishedByVenueInYear"+venue+"_"+year;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, PaperDTO.class));
        }
        List<PaperDTO> paperDTOList =  functional.getPapersPublishedByVenueInYear(venue, year);
        if(paperDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        cacheService.addListCache(key,paperDTOList,24,TimeUnit.HOURS);
        return ApiResultHandler.success(paperDTOList);

    }

    @PostMapping("/getAuthorsByVenue")
    public ApiResult getAuthorsByVenue(@RequestBody JSONObject jsonObject) {
        String venue = jsonObject.getString("venue");
        String key = "getAuthorsByVenue"+venue;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, AuthorDTO.class));
        }
        List<AuthorDTO> authorDTOList = functional.getAuthorsByVenue(venue);
        if(authorDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        cacheService.addListCache(key,authorDTOList,24,TimeUnit.HOURS);
        return ApiResultHandler.success(authorDTOList);
    }

    @PostMapping("/getPaperInVenueIsCitedTimes")
    public ApiResult getPaperInVenueIsCitedTimes(@RequestBody JSONObject jsonObject) {
        String venue = jsonObject.getString("venue");
        String key = "getPaperInVenueIsCitedTimes"+venue;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.get(key));
        }
        int papersInVenueIsCitedTimes = functional.getPapersInVenueIsCitedTimes(venue);
        cacheService.add(key,papersInVenueIsCitedTimes);
        return ApiResultHandler.success(papersInVenueIsCitedTimes);
    }

    @PostMapping("/getAuthorsInAffiliation")
    public ApiResult getAuthorsInAffiliation(@RequestBody JSONObject jsonObject) {
        String affiliation = jsonObject.getString("affiliation");
        String key = "getAuthorsInAffiliation"+affiliation;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, AuthorDTO.class));
        }
        List<AuthorDTO> authorDTOList = functional.findAuthorsInAffiliation(affiliation);
        if(authorDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        cacheService.addListCache(key,authorDTOList,24,TimeUnit.HOURS);
        return ApiResultHandler.success(authorDTOList);
    }

    @PostMapping("/getAuthorsBySameInterest")
    public ApiResult getAuthorsBySameInterest(@RequestBody JSONObject jsonObject) {
        String interest = jsonObject.getString("interest");
        String key = "getAuthorsBySameInterest"+interest;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, AuthorDTO.class));
        }
        List<AuthorDTO> authorDTOList = functional.findAuthorsByInterest(interest);
        if(authorDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        cacheService.addListCache(key,authorDTOList,24,TimeUnit.HOURS);
        return ApiResultHandler.success(authorDTOList);
    }

    @PostMapping("/getAllLinkedByAuthor")
    public ApiResult getAllLinkedByAuthor(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        String key = "getAllLinkedByAuthor"+authorId;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, BaseNode.class));
        }
        List<BaseNode> baseNodeList = functional.findAllByAuthor(authorId);
        if(baseNodeList.size() == 0)
            return ApiResultHandler.empty();
        cacheService.addListCache(key,baseNodeList,24,TimeUnit.HOURS);
        return ApiResultHandler.success(baseNodeList);
    }

    @PostMapping("/getMultihopBetweenNodes")
    public ApiResult getMultihopBetweenNodes(@RequestBody JSONObject jsonObject) {
        int hops = jsonObject.getInteger("hopCount");
        JSONObject entity1 = jsonObject.getJSONObject("entity1");
        String label1 = entity1.getString("label");
        String nodeId1 = entity1.getString(label1 + "Id");

        JSONObject entity2 = jsonObject.getJSONObject("entity2");
        String label2 = entity2.getString("label");
        String nodeId2 = entity2.getString(label2 + "Id");

        Long uid1 = functional.getUid(nodeId1, label1);
        Long uid2 = functional.getUid(nodeId2, label2);

        return ApiResultHandler.success(functional.MultihopBetweenNodes(uid1, uid2, hops));
    }

    @PostMapping("/getPapersByPaperTitle")
    public ApiResult getPapersByPaperTitle(@RequestBody JSONObject jsonObject){
        String paperTitle = jsonObject.getString("title");
        if (paperTitle == null || "".equals(paperTitle))
            return ApiResultHandler.fail();
        String key = "getPapersByPaperTitle"+paperTitle;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, PaperMysql.class));
        }
        List<PaperMysql> papers = functional.getPapersByPaperTitle(paperTitle);
        if (papers.size() ==0)
            return ApiResultHandler.empty();
        cacheService.addListCache(key,papers,24,TimeUnit.HOURS);
        return ApiResultHandler.success(papers);
    }

    @PostMapping("/getPaperAndReferences")
    public ApiResult getPaperAndReferences(@RequestBody JSONObject jsonObject) {
        String id = StringUtils.trim(jsonObject.getString("paperId"));
        if (id == null || "".equals(id))
            return ApiResultHandler.fail();
        String key = "getPaperAndReferences"+id;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getObject(key, PaperReferencesDTO.class));
        }
        PaperReferencesDTO paperAndReferences = functional.getPaperAndReferences(id);
        if(paperAndReferences != null)
            cacheService.add(key,paperAndReferences,24,TimeUnit.HOURS);
        return  ApiResultHandler.success(paperAndReferences);
    }

    @PostMapping("/getPaperAndVenue")
    public ApiResult getPaperAndVenue(@RequestBody JSONObject jsonObject){
        String id = StringUtils.trim(jsonObject.getString("paperId"));
        if (id == null || "".equals(id))
            return ApiResultHandler.fail();
        String key = "getPaperAndVenue"+id;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getObject(key, PaperAndVenueDTO.class));
        }
        PaperAndVenueDTO paperAndVenueByPaperId = functional.getPaperAndVenueByPaperId(id);
        if(paperAndVenueByPaperId !=null)
            cacheService.add(key,paperAndVenueByPaperId,24,TimeUnit.HOURS);
        return ApiResultHandler.success(paperAndVenueByPaperId);
    }
    @PostMapping("/getPaperAuthor")
    public ApiResult getPaperAuthor(@RequestBody JSONObject jsonObject){
        String id = StringUtils.trim(jsonObject.getString("paperId"));
        if (id == null || "".equals(id))
            return ApiResultHandler.fail();
        String key = "getPaperAuthor"+id;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getObject(key, PaperDTO.class));
        }
        PaperDTO paperDTO = functional.getPaperDTO(id);
        if(paperDTO != null)
            cacheService.add(key,paperDTO,24,TimeUnit.HOURS);
        return ApiResultHandler.success(paperDTO);
    }

    @PostMapping("/getInfluentialAuthors")
    public ApiResult getInfluentialAuthors(@RequestBody JSONObject jsonObject){
        String interest = StringUtils.trim(jsonObject.getString("interest"));
        String indicator = StringUtils.trim(jsonObject.getString("type"));
        String key = "getInfluentialAuthors"+interest+"_"+indicator;
        if(cacheService.hasKey(key)){
            return ApiResultHandler.success(cacheService.getList(key, AuthorDTO.class));
        }
        if(interest == null || interest.equals("") || indicator == null || indicator.equals(""))
            return null;
        List<AuthorDTO> influentialAuthors = functional.getInfluentialAuthors(interest, indicator);
        if(influentialAuthors.size() == 0)
            return ApiResultHandler.empty();
        cacheService.addListCache(key,influentialAuthors,24,TimeUnit.HOURS);
        return ApiResultHandler.success(influentialAuthors);
    }
}
