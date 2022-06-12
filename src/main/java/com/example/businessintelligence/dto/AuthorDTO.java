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

    public AuthorDTO() {
    }

    public AuthorDTO(int id, String name, double cn, double hi, double pc, double pi, double upi, String label) {
        this.id = id;
        this.name = name;
        this.cn = cn;
        this.hi = hi;
        this.pc = pc;
        this.pi = pi;
        this.upi = upi;
        this.label = label;
    }

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
