package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Interest;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "HAS_INTEREST")
public class HasInterest {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Author author;
    @EndNode
    private Interest interest;
}
