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
@NodeEntity(label = "PAPER")
public class Paper extends BaseNode {
    @Property("paperId")
    private String paperId;
    @Property("abstract")
    private String Abstract;
    @Property("title")
    private String title;
    @Property("year")
    private String year;

}
