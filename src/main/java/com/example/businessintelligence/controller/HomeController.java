package com.example.businessintelligence.controller;


import com.example.businessintelligence.dto.CollaborateDTO;
import com.example.businessintelligence.dto.WriteDTO;
import com.example.businessintelligence.entity.logicalEntity.ApiResult;

import com.example.businessintelligence.service.Functional;
import com.example.businessintelligence.utils.ApiResultHandler;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class HomeController {
    @Resource
    Functional functional;

    @GetMapping("/getPapersAndVenues")
    public ApiResult getPapersAndVenues(@RequestParam("author") String authorName) {
        WriteDTO writeDTO = functional.getPapersWrittenByAuthor(authorName);
        if(writeDTO == null) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(writeDTO);
    }
    @GetMapping("/getCollaborateAuthors")
    public ApiResult getCollaborateAuthors(@RequestParam("author") String authorName) {
        CollaborateDTO collaborateDTO = functional.getAuthorCollaboratedWithAuthor(authorName);
        if(collaborateDTO == null) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(collaborateDTO);
    }
    @GetMapping("/3")
    public ApiResult getVenuesPublishPaper(@RequestParam("paperId") int paperId) {
        return ApiResultHandler.buildApiResult(200,"",functional.getVenuesPublishPaper(String.valueOf(paperId)));
    }
    @GetMapping("/4")
    public ApiResult getAuthorsInSameAffiliation(@RequestParam("authorId") int authorId) {
        return ApiResultHandler.buildApiResult(200,"",functional.getAuthorsInSameAffiliation(String.valueOf(authorId)));
    }
}
