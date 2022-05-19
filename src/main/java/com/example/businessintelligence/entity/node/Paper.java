package com.example.businessintelligence.entity.node;

import com.example.businessintelligence.entity.relation.HasInterest;
import com.example.businessintelligence.entity.relation.Reference;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Builder
@Data
@Node("PAPER")
public class Paper {
    @Id
    private int paperId;
    @Property("abstract")
    private String Abstract;
    @Property("title")
    private String title;
    @Property("year")
    private int year;
    @Relationship(type = "REFERENCE", direction = Relationship.Direction.OUTGOING)
    private List<Reference> referenceList;
}
