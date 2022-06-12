package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface BaseRelationRepository extends Neo4jRepository<BaseRelation, Long> {

    @Query("match r=(n1) -[*2]- (n2) where ID(n1)=$uid1 and ID(n2)=$uid2 return r")
    List<BaseRelation> getMultihopBetweenNodes(Long uid1, Long uid2);





}
