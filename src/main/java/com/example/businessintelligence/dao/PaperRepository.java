package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaperRepository extends Neo4jRepository<Paper, Long> {

    @Query("match (p:PAPER{paperId:paperId}) return p")
    Paper get(String paperId);

    //根据名字查询作者写过哪些文章
    @Query("match (a:AUTHOR{name:$authorName}) -[:WRITE]-> (p:PAPER) return p")
    List<Paper> findPapersWrittenByAuthorName(String authorName);

    //根据id查询作者写过哪些文章
    @Query("match (a:AUTHOR{authorId:$authorId}) -[:WRITE]-> (p:PAPER) return p")
    List<Paper> findPapersWrittenByAuthorId(String authorId);

    //查询作者引用过哪些文章
    @Query("match (a:AUTHOR{authorId:$authorId}) -[:WRITE]-> (p:PAPER) -[:REFERENCE]- (p1:PAPER) return p1")
    List<Paper> findPapersCitedByAuthor(String authorId);

    //某年某刊物发表的全部文章（统计数量等）
    @Query("match (v:VENUE{venueId:$venueId}) -[:PUBLISH]-> (p:PAPER) where p.year=$year return p")
    List<Paper> findPapersPublishedByVenueAndYear(String venueId, String year);

    //计算该文章的被引用次数
    @Query("match (p:PAPER{paperId:$paperId}) <-[:REFERENCE]- (p1:PAPER) return count(distinct p1)")
    int getPaperIdCitedTimes(String paperId);
}
