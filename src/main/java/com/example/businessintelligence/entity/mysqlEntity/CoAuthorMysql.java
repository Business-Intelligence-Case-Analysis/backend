package com.example.businessintelligence.entity.mysqlEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName CoAuthorMysql.java
 * @Description TODO
 * @createTime 2022年06月11日 20:30:00
 */
@Entity
@IdClass(CoAutorPk.class)
@Table(name = "co_author",schema = "business")
public class CoAuthorMysql {
    private int authorId1;
    private int authorId2;
    private int co_count;

    @Id
    @Column(name = "authorId1")
    public int getAuthorId1() {
        return authorId1;
    }

    public void setAuthorId1(int authorId1) {
        this.authorId1 = authorId1;
    }

    @Id
    @Column(name = "authorId2")
    public int getAuthorId2() {
        return authorId2;
    }

    public void setAuthorId2(int authorId2) {
        this.authorId2 = authorId2;
    }

    @Basic
    @Column(name = "co_count")
    public int getCo_count() {
        return co_count;
    }

    public void setCo_count(int co_count) {
        this.co_count = co_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoAuthorMysql that = (CoAuthorMysql) o;
        return authorId1 == that.authorId1 && authorId2 == that.authorId2 && co_count == that.co_count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId1, authorId2, co_count);
    }
}
