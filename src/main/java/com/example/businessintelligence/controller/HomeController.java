package com.example.businessintelligence.controller;


import com.example.businessintelligence.entity.logicalEntity.ApiResult;

import com.example.businessintelligence.service.Functional;
import com.example.businessintelligence.utils.ApiResultHandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HomeController {
    @Resource
    Functional functional;

    @GetMapping("/")
    public ApiResult getPapers(@RequestParam("authorId") int authorId) {
        return ApiResultHandler.buildApiResult(200,"",functional.getPapersWrittenByAuthor(String.valueOf(authorId)));
    }
}
