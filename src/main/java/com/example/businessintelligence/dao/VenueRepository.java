package com.example.businessintelligence.dao;

import com.example.businessintelligence.entity.node.Venue;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface VenueRepository extends Neo4jRepository<Venue, String> {
    //查找论文发表在哪些期刊
    @Query("match (v:VENUE) -[:PUBLISH]-> (p:PAPER{paperId:$paperId}) return v")
    List<Venue> findVenuesPublishPaper(String paperId);

    //这个学术刊物/会议的所有文章的被引用次数之和
    @Query("match (v:VENUE{venueId:$venueId}) -[:PUBLISH]-> (p:PAPER) <-[:REFERENCE]- (p1:PAPER) return count(distinct p1)")
    int getPapersInVenueIsCitedTimes(String venueId);

    //查找论文发表在哪些期刊
    @Query("match (v:VENUE) -[:PUBLISH]-> (p:PAPER{paperId:$paperId}) return v")
    Venue findVenueByPublishPaperId(String paperId);
}
