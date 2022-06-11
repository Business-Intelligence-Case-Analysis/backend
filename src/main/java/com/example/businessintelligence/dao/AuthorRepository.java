package com.example.businessintelligence.dao;

import com.example.businessintelligence.dto.AuthorDTO;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Integer> {

    Author findAuthorByAuthorId(String id);

    //根据名字查询作者节点（可能同名）
    List<Author> findAuthorByName(String authorName);

    //根据id查询一个作者的合作者
    @Query("match (a:AUTHOR{authorId:$authorId}) -[:COLLABORATE]-> (a1:AUTHOR) return a1")
    List<Author> findCollaborateAuthorById(String authorId);

    //根据名字查询一个作者的合作者
    @Query("match (a:AUTHOR{name:$authorName}) -[:COLLABORATE]-> (a1:AUTHOR) return a1")
    List<Author> findCollaborateAuthorByName(String authorName);

    //查询与一个作者同属一个机构下的所有同事
    @Query("match (a:AUTHOR{authorId:$authorId}) -[:IN]-> (aff:AFFILIATION) <-[:IN]- (ans:AUTHOR) return ans")
    List<Author> findAuthorsInSameAffiliation(String authorId);

    //查询一篇文章的作者
    @Query("match (a:AUTHOR) -[:WRITE]-> (p:PAPER{paperId:$paperId}) return a")
    List<Author> findAuthorWritingPaper(String paperId);

    //哪些作者在某期刊上发表过文章
    @Query("match (v:VENUE{venueId:$venueId}) -[:PUBLISH]-> (p:PAPER) <-[:WRITE]- (a:AUTHOR) return a")
    List<Author> findAuthorsByVenue(String venueId);

}
