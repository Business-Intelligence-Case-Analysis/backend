package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Affiliation;
import com.example.businessintelligence.entity.node.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.*;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity(type = "IN")
public class In extends BaseRelation {
    @StartNode
    private Author author;
    @EndNode
    private Affiliation affiliation;
}
