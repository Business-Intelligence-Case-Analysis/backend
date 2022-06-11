package com.example.businessintelligence.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.businessintelligence.dto.*;
import com.example.businessintelligence.entity.logicalEntity.ApiResult;

import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.service.Functional;
import com.example.businessintelligence.utils.ApiResultHandler;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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



}
