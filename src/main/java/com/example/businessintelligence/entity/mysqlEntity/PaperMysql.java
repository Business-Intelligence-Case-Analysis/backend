package com.example.businessintelligence.entity.mysqlEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName PaperMysql.java
 * @Description TODO
 * @createTime 2022年06月11日 16:21:00
 */
@Entity
@Table(name = "paper" ,schema = "business")
public class PaperMysql {
    private int paperId;
    private String title;
    private String authors;
    private String affiliations;
    private int year;
    private String venue;
    private String references;
    private String paperAbstract;

    @Id
    @Column(name = "paper_id")
    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "authors")
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    @Basic
    @Column(name = "affiliations")
    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "venue")
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Basic
    @Column(name = "references")
    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    @Basic
    @Column(name = "abstract")
    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperMysql that = (PaperMysql) o;
        return paperId == that.paperId && year == that.year && Objects.equals(title, that.title) && Objects.equals(authors, that.authors) && Objects.equals(affiliations, that.affiliations) && Objects.equals(venue, that.venue) && Objects.equals(references, that.references) && Objects.equals(paperAbstract, that.paperAbstract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperId, title, authors, affiliations, year, venue, references, paperAbstract);
    }
}
