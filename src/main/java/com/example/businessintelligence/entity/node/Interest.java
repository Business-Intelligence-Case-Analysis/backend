package com.example.businessintelligence.entity.node;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@Builder
@Data
@NodeEntity(label = "INTEREST")
public class Interest {
    @Id
    private String interestId;
}
