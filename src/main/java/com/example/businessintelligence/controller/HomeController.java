package com.example.businessintelligence.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.businessintelligence.dto.CollaborateDTO;
import com.example.businessintelligence.dto.AuthorAndPaperDTO;
import com.example.businessintelligence.entity.logicalEntity.ApiResult;

import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.service.Functional;
import com.example.businessintelligence.utils.ApiResultHandler;

import netscape.javascript.JSObject;
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
        String name = jsonObject.getInteger("author").toString();
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
        CollaborateDTO collaborateDTO = functional.getAuthorCollaboratedWithAuthor(authorId);
        if(collaborateDTO == null) {
            return ApiResultHandler.empty();
        }
        return ApiResultHandler.success(collaborateDTO);
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

    @GetMapping("/3")
    public ApiResult getVenuesPublishPaper(@RequestParam("paperId") int paperId) {
        return ApiResultHandler.buildApiResult(200,"",functional.getVenuesPublishPaper(String.valueOf(paperId)));
    }
    @GetMapping("/4")
    public ApiResult getAuthorsInSameAffiliation(@RequestParam("authorId") int authorId) {
        return ApiResultHandler.buildApiResult(200,"",functional.getAuthorsInSameAffiliation(String.valueOf(authorId)));
    }
}
