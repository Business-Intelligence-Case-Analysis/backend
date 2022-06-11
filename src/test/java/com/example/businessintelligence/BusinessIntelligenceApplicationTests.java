package com.example.businessintelligence;

import com.example.businessintelligence.dao.AuthorRepository;
import org.junit.jupiter.api.Test;
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

}
