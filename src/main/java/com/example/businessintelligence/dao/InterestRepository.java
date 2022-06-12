package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Interest;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface InterestRepository extends Neo4jRepository<Interest, Long> {
    @Query("match (in:INTEREST{interestId:$interestId}) return in")
    Interest get(String interestId);
}
