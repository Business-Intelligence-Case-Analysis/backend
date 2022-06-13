package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Map;

public interface BaseRelationRepository extends Neo4jRepository<BaseRelation, Long> {

//    @Query("match r=(n1) -[*2]- (n2) where ID(n1)=$uid1 and ID(n2)=$uid2 return r")
//    List<BaseRelation> getMultihopBetweenNodes(Long uid1, Long uid2);

    @Query("match(n1) match(n2) where ID(n1)=$uid1 and ID(n2)=$uid2 call apoc.path.expandConfig(n1, {minLevel:1,maxLevel:$param,endNodes:[n2]}) yield path return path")
    List<BaseRelation> getMultihopBetweenNodes(Long uid1, Long uid2, int param);


}
