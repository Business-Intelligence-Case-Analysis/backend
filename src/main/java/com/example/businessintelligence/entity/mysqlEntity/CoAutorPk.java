package com.example.businessintelligence.entity.mysqlEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName CoAutorPk.java
 * @Description TODO
 * @createTime 2022年06月11日 20:39:00
 */
public class CoAutorPk implements Serializable {
    private int authorId1;
    private int authorId2;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoAutorPk coAutorPk = (CoAutorPk) o;
        return authorId1 == coAutorPk.authorId1 && authorId2 == coAutorPk.authorId2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId1, authorId2);
    }
}
