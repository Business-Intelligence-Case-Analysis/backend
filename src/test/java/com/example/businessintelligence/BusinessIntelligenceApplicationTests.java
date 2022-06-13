package com.example.businessintelligence;

import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.BaseRelationRepository;
import com.example.businessintelligence.dao.PaperRepository;
import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Paper;
import org.junit.jupiter.api.Test;
import com.example.businessintelligence.dao.mysqlDao.PaperRepositoryMysql;
import com.example.businessintelligence.entity.mysqlEntity.PaperMysql;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//        List<BaseRelation> baseRelations = baseRelationRepository.test(5958579L, 1489531L,2);
//        System.out.println(baseRelations.size());
//        System.out.println(baseRelations);
    }

    @Test
    void test() {
        List<Paper> papers = paperRepository.findPapersPublishedByVenueAndYear("Information and Control", "1984");
    }


    @Autowired
    PaperRepositoryMysql paperRepositoryMysql;

    @Test
    void f(){
        PaperMysql p = paperRepositoryMysql.findPaperMysqlByPaperId(47);
        System.out.println(p == null);
    }
}
