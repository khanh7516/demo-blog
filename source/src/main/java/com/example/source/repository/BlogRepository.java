package com.example.source.repository;

import com.example.source.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}