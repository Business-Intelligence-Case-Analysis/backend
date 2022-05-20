package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Paper;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "REFERENCE")
public class Reference {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Paper paper1;
    @EndNode
    private Paper paper2;
}
