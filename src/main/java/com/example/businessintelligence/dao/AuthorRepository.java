package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Integer> {
    //查询一个作者的合作者
    @Query("match (a:AUTHOR{authorId:$authorId}) -[:COLLABORATE]-> (a1:AUTHOR) return a1")
    List<Author> findCollaborateAuthor(String authorId);
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
