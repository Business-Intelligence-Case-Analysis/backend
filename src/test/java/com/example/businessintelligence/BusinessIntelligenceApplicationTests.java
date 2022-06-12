package com.example.businessintelligence;

import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.BaseRelationRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Paper;
import org.junit.jupiter.api.Test;


import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BusinessIntelligenceApplicationTests {

    @Resource
    AuthorRepository authorRepository;
    @Resource
    BaseRelationRepository baseRelationRepository;
    @Resource
    PaperRepository paperRepository;
    @Test
    void contextLoads() {
//        List<BaseNode> b = baseRelationRepository.get("AUTHOR");
//        List<BaseRelation> b = baseRelationRepository.getMultihopBetweenNodes(158589L,1140812L,2);
//        System.out.println(b);
    }

    @Test
    void test() {
        List<Paper> papers = paperRepository.findPapersPublishedByVenueAndYear("Information and Control", "1984");
    }

}
