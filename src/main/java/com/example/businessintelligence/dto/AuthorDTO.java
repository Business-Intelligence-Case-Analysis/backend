package com.example.businessintelligence.dto;

import com.example.businessintelligence.entity.node.Author;
import lombok.Data;

@Data
public class AuthorDTO {

    private int id;

    private String name;

    private double cn;

    private double hi;

    private double pc;

    private double pi;

    private double upi;

    private String label;

    public AuthorDTO(Author author) {
        System.out.println(author);
        this.id = Integer.parseInt(author.getAuthorId());
        this.name = author.getName();
        this.cn = Double.parseDouble(author.getCn());
        this.hi = Double.parseDouble(author.getHi());
        this.pc = Double.parseDouble(author.getPc());
        this.pi = Double.parseDouble(author.getPi());
        this.upi = Double.parseDouble(author.getUpi());
        this.label = "author";
    }
}
