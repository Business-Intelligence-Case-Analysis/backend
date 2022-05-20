# Spring Data Neo4j

## Version
org.springframework.boot: spring-boot-starter-data-neo4j: 2.3.5.RELEASE

org.springframework.data: spring-data-neo4j: 5.3.5.RELEASE

## application.properties
spring.data.neo4j.uri = {xxx}:7687

spring.data.neo4j.username = {usr}

spring.data.neo4j.password = {pwd}

server.port = {port}

## Node
Affiliation： 机构

Author： 作者

Interest： 兴趣

Paper： 论文

Venue： 发表场合

## Relation
Collaborate： （作者与作者）合作

HasInterest： （作者与兴趣）拥有兴趣

In：（作者与机构）位于

Publish： （场合与论文）发表

Reference：（论文与论文）引用

Write： （作者与论文）撰写