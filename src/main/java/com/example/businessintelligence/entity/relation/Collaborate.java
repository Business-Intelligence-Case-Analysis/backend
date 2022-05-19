package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Author;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Collaborate {
    @RelationshipId
    private Long id;
    @TargetNode
    private Author collaborateAuthor;
    @Property("number")
    private int number;
}
