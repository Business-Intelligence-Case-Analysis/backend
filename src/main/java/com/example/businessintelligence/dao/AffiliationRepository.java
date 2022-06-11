package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Affiliation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface AffiliationRepository extends Neo4jRepository<Affiliation, String> {

    @Query("match (a:AUTHOR{authorId:$authorId}) -[:IN]-> (aff:AFFILIATION) return aff")
    List<Affiliation> findAffiliationsByAuthorId(String authorId);
}
