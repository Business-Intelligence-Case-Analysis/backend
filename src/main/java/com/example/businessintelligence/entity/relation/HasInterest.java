package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Interest;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class HasInterest {
    @RelationshipId
    private Long id;
    @TargetNode
    private Interest interest;
}
