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

    List<AuthorDTO> author;

    public void setPaperAndPeriodical(Paper paper, List<PeriodicalDTO> periodicalDTOList) {
        this.id = Integer.parseInt(paper.getPaperId());
        this.title = paper.getTitle();
        this.year = paper.getYear();
        this.Abstract = paper.getAbstract();
        this.label = "paper";
        this.periodical = periodicalDTOList;
    }

    public void setPaperAndAuthor(Paper paper, List<AuthorDTO> authorDTOList) {
        this.id = Integer.parseInt(paper.getPaperId());
        this.title = paper.getTitle();
        this.year = paper.getYear();
        this.Abstract = paper.getAbstract();
        this.label = "paper";
        this.author = authorDTOList;
    }



}
