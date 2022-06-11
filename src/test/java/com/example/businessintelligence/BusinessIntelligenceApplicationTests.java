package com.example.businessintelligence;

import com.example.businessintelligence.dao.AuthorRepository;
import com.example.businessintelligence.dao.mysqlDao.PaperRepositoryMysql;
import com.example.businessintelligence.entity.mysqlEntity.PaperMysql;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BusinessIntelligenceApplicationTests {

    @Resource
    AuthorRepository authorRepository;
    @Test
    void contextLoads() {
        System.out.println(authorRepository.findAuthorByAuthorId("1"));

        System.out.println(authorRepository.findAuthorByName("O. Willum"));
    }


    @Autowired
    PaperRepositoryMysql paperRepositoryMysql;

    @Test
    void f(){
        PaperMysql p = paperRepositoryMysql.findPaperMysqlByPaperId(47);
        System.out.println(p == null);
    }
}
