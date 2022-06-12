package com.example.businessintelligence.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.businessintelligence.dto.*;
import com.example.businessintelligence.entity.logicalEntity.ApiResult;
import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.mysqlEntity.PaperMysql;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.service.Functional;
import com.example.businessintelligence.utils.ApiResultHandler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class HomeController {
    @Resource
    Functional functional;

    @PostMapping("/getAuthorsByName")
    public ApiResult getAuthorsByName(@RequestBody JSONObject jsonObject) {
        String name = jsonObject.getString("author");
        List<Author> authorList = functional.getAuthorsByName(name);
        if(authorList.size() == 0) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorList);
    }

    @PostMapping("/getPapersAndVenues")
    public ApiResult getPapersAndVenues(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        AuthorAndPaperDTO authorAndPaperDTO = functional.getPapersWrittenByAuthor(authorId);
        if(authorAndPaperDTO == null) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorAndPaperDTO);
    }

    @PostMapping("/getCollaborateAuthors")
    public ApiResult getCollaborateAuthors(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        AuthorAndCollaboratorDTO authorAndCollaboratorDTO = functional.getAuthorCollaboratedWithAuthor(authorId);
        if(authorAndCollaboratorDTO == null) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorAndCollaboratorDTO);
    }

        @PostMapping("/getPapersAndAuthorsCitedByAuthor")
    public ApiResult getPapersAndAuthorsCitedByAuthor(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        AuthorAndPaperDTO authorAndPaperDTO = functional.getPapersCitedByAuthor(authorId);
        if(authorAndPaperDTO == null) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorAndPaperDTO);
    }

    @PostMapping("/getAuthorsInSameAffiliation")
    public ApiResult getAuthorsInSameAffiliation(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        AuthorAndAffiliationDTO authorAndAffiliationDTO = functional.getAuthorsInSameAffiliation(authorId);
        if(authorAndAffiliationDTO == null) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorAndAffiliationDTO);
    }

    @PostMapping("/getPapersPublishedByVenueInYear")
    public ApiResult getPapersPublishedByVenueInYear(@RequestBody JSONObject jsonObject) {
        String venue = jsonObject.getString("venue");
        String year = jsonObject.getInteger("year").toString();
        List<PaperDTO> paperDTOList =  functional.getPapersPublishedByVenueInYear(venue, year);
        if(paperDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(paperDTOList);

    }

    @PostMapping("/getAuthorsByVenue")
    public ApiResult getAuthorsByVenue(@RequestBody JSONObject jsonObject) {
        String venue = jsonObject.getString("venue");
        List<AuthorDTO> authorDTOList = functional.getAuthorsByVenue(venue);
        if(authorDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorDTOList);
    }

    @PostMapping("/getPaperInVenueIsCitedTimes")
    public ApiResult getPaperInVenueIsCitedTimes(@RequestBody JSONObject jsonObject) {
        String venue = jsonObject.getString("venue");
        return ApiResultHandler.success(functional.getPapersInVenueIsCitedTimes(venue));
    }

    @PostMapping("/getAuthorsInAffiliation")
    public ApiResult getAuthorsInAffiliation(@RequestBody JSONObject jsonObject) {
        String affiliation = jsonObject.getString("affiliation");
        List<AuthorDTO> authorDTOList = functional.findAuthorsInAffiliation(affiliation);
        if(authorDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorDTOList);
    }

    @PostMapping("/getAuthorsBySameInterest")
    public ApiResult getAuthorsBySameInterest(@RequestBody JSONObject jsonObject) {
        String interest = jsonObject.getString("interest");
        List<AuthorDTO> authorDTOList = functional.findAuthorsByInterest(interest);
        if(authorDTOList.size() == 0) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(authorDTOList);
    }

    @PostMapping("/getAllLinkedByAuthor")
    public ApiResult getAllLinkedByAuthor(@RequestBody JSONObject jsonObject) {
        String authorId = jsonObject.getInteger("authorId").toString();
        List<BaseNode> baseNodeList = functional.findAllByAuthor(authorId);
        return ApiResultHandler.success(baseNodeList);
    }

    @PostMapping("/getMultihopBetweenNodes")
    public ApiResult getMultihopBetweenNodes(@RequestBody JSONObject jsonObject) {
        JSONObject entity1 = jsonObject.getJSONObject("entity1");
        String label1 = entity1.getString("label");
        String nodeId1 = entity1.getString(label1 + "Id");

        JSONObject entity2 = jsonObject.getJSONObject("entity2");
        String label2 = entity2.getString("label");
        String nodeId2 = entity2.getString(label2 + "Id");

        Long uid1 = functional.getUid(nodeId1, label1);
        Long uid2 = functional.getUid(nodeId2, label2);

        return ApiResultHandler.success(functional.MultihopBetweenNodes(uid1, uid2, 2));
    }

    @PostMapping("/getPapersByPaperTitle")
    public ApiResult getPapersByPaperTitle(@RequestBody JSONObject jsonObject){
        String paperTitle = jsonObject.getString("title");
        if (paperTitle == null || "".equals(paperTitle))
            return ApiResultHandler.fail();
        List<PaperMysql> papers = functional.getPapersByPaperTitle(paperTitle);
        if (papers.size() ==0)
            return ApiResultHandler.empty();
        return ApiResultHandler.success(papers);
    }

    @PostMapping("/getPaperAndReferences")
    public ApiResult getPaperAndReferences(@RequestBody JSONObject jsonObject) {
        String id = StringUtils.trim(jsonObject.getString("paperId"));
        if (id == null || "".equals(id))
            return ApiResultHandler.fail();
        return  ApiResultHandler.success(functional.getPaperAndReferences(id));
    }

    @PostMapping("/getPaperAndVenue")
    public ApiResult getPaperAndVenue(@RequestBody JSONObject jsonObject){
        String id = StringUtils.trim(jsonObject.getString("paperId"));
        if (id == null || "".equals(id))
            return ApiResultHandler.fail();
        return ApiResultHandler.success(functional.getPaperAndVenueByPaperId(id));
    }
    @PostMapping("/getPaperAuthor")
    public ApiResult getPaperAuthor(@RequestBody JSONObject jsonObject){
        String id = StringUtils.trim(jsonObject.getString("paperId"));
        if (id == null || "".equals(id))
            return ApiResultHandler.fail();
        return ApiResultHandler.success(functional.getPaperDTO(id));
    }

    @PostMapping("/getInfluentialAuthors")
    public ApiResult getInfluentialAuthors(@RequestBody JSONObject jsonObject){
        String interest = StringUtils.trim(jsonObject.getString("interest"));
        String indicator = StringUtils.trim(jsonObject.getString("type"));
        if(interest == null || interest.equals("") || indicator == null || indicator.equals(""))
            return null;
        else return ApiResultHandler.success(functional.getInfluentialAuthors(interest,indicator));
    }
}
