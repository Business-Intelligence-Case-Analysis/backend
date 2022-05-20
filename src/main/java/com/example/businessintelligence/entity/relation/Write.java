package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "WRITE")
public class Write {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Author author;
    @EndNode
    private Paper paper;
}
