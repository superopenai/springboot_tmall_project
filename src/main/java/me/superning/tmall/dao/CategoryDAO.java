package me.superning.tmall.dao;


import me.superning.tmall.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDAO extends JpaSpecificationExecutor<Category>,
        JpaRepository<Category, Integer> {

    Category findById(int id);

    //    @Query(value = "select * from category where id limit ? off", nativeQuery = true)
//    Slice<Category> findByIdLimit(int id);



//    @Query(value="SELECT * FROM category ORDER BY id  limit ?1 offset ?2", nativeQuery = true)
//     Page<Category> findByIdLimit(int limit, int offset, Pageable pageable);
}