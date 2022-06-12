package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface BaseNodeRepository extends Neo4jRepository<BaseNode, Long> {
    //查询某一作者的所有关联节点
    @Query("match (a:AUTHOR{authorId:$authorId}) -[]- (p) return p")
    List<BaseNode> findAllLinkedByAuthor(String authorId);

    //查询某个标签的某个结点
//    @Query("match p=(n) where all(l in nodes(p) where labels(l)=[$label]) return p limit 10")
//    List<BaseNode> get(String label);

}
