package com.example.businessintelligence.entity.mysqlEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName AuthorMysql.java
 * @Description TODO
 * @createTime 2022年06月11日 20:22:00
 */
public class AuthorMysql {
    private int authorId;
    private String authorName;
    private String affiliations;
    private int pc;
    private int cn;
    private int hi;
    private double pi;
    private double upi;
    private String theme;

    @Basic
    @Column(name = "theme")
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Id
    @Column(name = "authorId")
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "name")
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
    @Column(name = "pc")
    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    @Basic
    @Column(name = "cn")
    public int getCn() {
        return cn;
    }

    public void setCn(int cn) {
        this.cn = cn;
    }

    @Basic
    @Column(name = "hi")
    public int getHi() {
        return hi;
    }

    public void setHi(int hi) {
        this.hi = hi;
    }

    @Basic
    @Column(name = "pi")
    public double getPi() {
        return pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    @Basic
    @Column(name = "upi")
    public double getUpi() {
        return upi;
    }

    public void setUpi(double upi) {
        this.upi = upi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorMysql that = (AuthorMysql) o;
        return authorId == that.authorId && pc == that.pc && cn == that.cn && hi == that.hi && Double.compare(that.pi, pi) == 0 && Double.compare(that.upi, upi) == 0 && Objects.equals(authorName, that.authorName) && Objects.equals(affiliations, that.affiliations) && Objects.equals(theme, that.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName, affiliations, pc, cn, hi, pi, upi, theme);
    }
}
