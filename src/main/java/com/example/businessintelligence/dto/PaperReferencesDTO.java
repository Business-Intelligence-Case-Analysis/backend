package com.example.businessintelligence.dto;

import com.example.businessintelligence.entity.node.Paper;
import lombok.Data;

import java.util.List;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName PaperReferencesDTO.java
 * @Description TODO
 * @createTime 2022年06月11日 21:17:00
 */
@Data
public class PaperReferencesDTO {
    private PaperDTO paper;

    private List<PaperDTO> reference;

    public PaperReferencesDTO(PaperDTO paper, List<PaperDTO> reference) {
        this.paper = paper;
        this.reference = reference;
    }
}
