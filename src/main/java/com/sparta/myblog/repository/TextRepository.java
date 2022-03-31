package com.sparta.myblog.repository;

import com.sparta.myblog.models.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findAllByBlogIdOrderByCreatedAtDesc(Long blogId);
}
