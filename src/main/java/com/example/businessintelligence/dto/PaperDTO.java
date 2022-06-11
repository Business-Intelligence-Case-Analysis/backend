package com.example.businessintelligence.dto;


import com.example.businessintelligence.entity.node.Paper;
import lombok.Data;

import java.util.List;

@Data
public class PaperDTO {
    int id;

    String title;

    String year;

    String Abstract;

    String label;

    List<PeriodicalDTO> periodical;

    public PaperDTO(Paper paper, List<PeriodicalDTO> periodicalDTOList) {
        this.id = Integer.parseInt(paper.getPaperId());
        this.title = paper.getTitle();
        this.year = paper.getYear();
        this.Abstract = paper.getAbstract();
        this.label = "paper";
        this.periodical = periodicalDTOList;
    }



}
