package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Interest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.*;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity(type = "HAS_INTEREST")
public class HasInterest extends BaseRelation {
    @StartNode
    private Author author;
    @EndNode
    private Interest interest;
}
