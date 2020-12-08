package com.codegym.repository;

import com.codegym.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByAuthorContaining(Pageable pageable, String author);

    @Query(value = "select * from blog group by id order by `date`", nativeQuery = true)
    List<Blog> findAllBlogAndSort();
}
