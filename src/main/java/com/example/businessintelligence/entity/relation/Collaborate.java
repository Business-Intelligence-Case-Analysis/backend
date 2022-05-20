package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Author;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "COLLABORATE")
public class Collaborate {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Author author1;
    @EndNode
    private Author author2;
    @Property("number")
    private int number;
}
