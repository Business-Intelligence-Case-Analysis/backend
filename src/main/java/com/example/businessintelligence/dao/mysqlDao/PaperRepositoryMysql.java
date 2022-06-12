package com.example.businessintelligence.dao.mysqlDao;

import com.example.businessintelligence.entity.mysqlEntity.PaperMysql;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName PaperRepositoryMysql.java
 * @Description TODO
 * @createTime 2022年06月11日 19:53:00
 */
@Repository
public interface PaperRepositoryMysql extends JpaRepository<PaperMysql, Integer> {

    // 查询同名论文
    List<PaperMysql> findPaperMysqlByTitle(String title);

    @Query(value = "select * from paper where paper_id=?1",nativeQuery = true)
    PaperMysql findPaperMysqlByPaperId(int id);

}
