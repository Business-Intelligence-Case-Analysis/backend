package com.example.businessintelligence.entity.node;

import com.example.businessintelligence.entity.relation.HasInterest;
import com.example.businessintelligence.entity.relation.Publish;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Builder
@Data
@Node("VENUE")
public class Venue {
    @Id
    private String venueId;
    @Relationship(type = "PUBLISH", direction = Relationship.Direction.OUTGOING)
    private List<Publish> publishList;
}
