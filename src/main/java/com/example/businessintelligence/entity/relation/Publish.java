package com.example.businessintelligence.entity.relation;

import com.example.businessintelligence.entity.node.Paper;
import com.example.businessintelligence.entity.node.Venue;
import org.neo4j.ogm.annotation.*;

import javax.xml.ws.WebEndpoint;


@RelationshipEntity(type = "PUBLISH")
public class Publish {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Venue venue;
    @EndNode
    private Paper paper;
}
