package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Affiliation;
import com.example.businessintelligence.entity.node.Author;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "IN")
public class In {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Author author;
    @EndNode
    private Affiliation affiliation;
}
