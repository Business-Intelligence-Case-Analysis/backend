package com.example.businessintelligence.dto;

import lombok.Data;

@Data
public class BaseRelationDTO {

    String source; //起始结点id

    String target; //终止节点id

    String relationship; //关系类型

    public BaseRelationDTO(String source, String target, String relationship) {
        this.source = source;
        this.target = target;
        this.relationship = relationship;
    }
}
