package com.example.businessintelligence.entity.node;

import com.example.businessintelligence.entity.relation.Collaborate;
import com.example.businessintelligence.entity.relation.HasInterest;
import com.example.businessintelligence.entity.relation.In;
import com.example.businessintelligence.entity.relation.Write;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;


@Builder
@Data
@Node("AUTHOR")
public class Author {
    @Id
    private Integer authorId;
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
    @Relationship(type = "COLLABORATE", direction = Relationship.Direction.OUTGOING)
    private List<Collaborate> collaborateList;
    @Relationship(type = "HAS_INTEREST", direction = Relationship.Direction.OUTGOING)
    private List<HasInterest> hasInterestList;
    @Relationship(type = "WRITE", direction = Relationship.Direction.OUTGOING)
    private List<Write> writeList;
    @Relationship(type = "IN", direction = Relationship.Direction.OUTGOING)
    private List<In> inList;

}
