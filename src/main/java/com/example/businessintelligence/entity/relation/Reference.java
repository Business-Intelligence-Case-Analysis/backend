package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Paper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.*;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity(type = "REFERENCE")
public class Reference extends BaseRelation {
    @StartNode
    private Paper paper1;
    @EndNode
    private Paper paper2;
}
