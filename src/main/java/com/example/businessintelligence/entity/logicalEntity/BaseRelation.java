package com.example.businessintelligence.entity.logicalEntity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@Data
public class BaseRelation {
    @Id
    @GeneratedValue
    private Long id;
}
