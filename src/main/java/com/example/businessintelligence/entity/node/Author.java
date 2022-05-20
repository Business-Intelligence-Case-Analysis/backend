package com.example.businessintelligence.entity.node;


import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@Builder
@Data
@NodeEntity(label = "AUTHOR")
public class Author {
    @Id
    private String authorId;
    @Property("name")
    private String name;
    @Property("cn")
    private String cn;
    @Property("hi")
    private String hi;
    @Property("pc")
    private String pc;
    @Property("pi")
    private String pi;
    @Property("upi")
    private String upi;

}
