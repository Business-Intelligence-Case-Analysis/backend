package com.example.businessintelligence.entity.node;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Builder
@Data
@Node("AFFILIATION")
public class Affiliation {
    @Id
    private String affiliationId;
}
