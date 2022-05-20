package com.example.businessintelligence.service;

import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.entity.node.Paper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Functional {
    @Resource
    PaperRepository paperRepository;
    public List<Paper> getPapersWrittenByAuthor(String authorId) {
        return paperRepository.findPapersWrittenByAuthor(authorId);
    }

}
