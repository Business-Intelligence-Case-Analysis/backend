package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Author;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface AuthorRepository extends ReactiveNeo4jRepository<Author, Integer> {
}
