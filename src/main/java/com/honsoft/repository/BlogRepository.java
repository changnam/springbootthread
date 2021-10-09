package com.honsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.honsoft.entity.Blog;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
