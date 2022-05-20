package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Integer> {


}
