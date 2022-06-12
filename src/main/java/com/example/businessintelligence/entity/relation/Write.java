package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.logicalEntity.BaseRelation;
import com.example.businessintelligence.entity.node.Author;
import com.example.businessintelligence.entity.node.Paper;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.*;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity(type = "WRITE")
public class Write extends BaseRelation {
    @StartNode
    private Author author;
    @EndNode
    private Paper paper;
}
