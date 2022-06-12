package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.*;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity(type = "COLLABORATE")
public class Collaborate extends BaseRelation {
    @StartNode
    private Author author1;
    @EndNode
    private Author author2;
    @Property("number")
    private int number;
}
