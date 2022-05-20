package com.example.businessintelligence.entity.node;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@Builder
@Data
@NodeEntity(label = "PAPER")
public class Paper {
    @Id
    private String paperId;
    @Property("abstract")
    private String Abstract;
    @Property("title")
    private String title;
    @Property("year")
    private String year;

}
