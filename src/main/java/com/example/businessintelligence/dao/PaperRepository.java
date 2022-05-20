package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Paper;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaperRepository extends Neo4jRepository<Paper, Integer> {
    @Query("match (a:AUTHOR{authorId:$authorId}) -[:WRITE]-> (p:PAPER) return p")
    List<Paper> findPapersWrittenByAuthor(String authorId);
}
