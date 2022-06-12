package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Paper;
import com.example.businessintelligence.entity.node.Venue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.*;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity(type = "PUBLISH")
public class Publish extends BaseRelation {
    @StartNode
    private Venue venue;
    @EndNode
    private Paper paper;
}
