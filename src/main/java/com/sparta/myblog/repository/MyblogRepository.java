package com.sparta.myblog.repository;

import com.sparta.myblog.models.Myblog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MyblogRepository extends JpaRepository<Myblog,Long> {
    List<Myblog> findAllByModifiedAtBetweenOrderByCreatedAtDesc(LocalDateTime start, LocalDateTime end);
}
