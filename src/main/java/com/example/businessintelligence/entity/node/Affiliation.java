package com.example.businessintelligence.entity.node;

import com.example.businessintelligence.entity.logicalEntity.BaseNode;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NodeEntity(label = "AFFILIATION")
public class Affiliation extends BaseNode {
    @Property("affiliationId")
    private String affiliationId;
}
