package com.sparta.myblog.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyblogRepository extends JpaRepository<Myblog,Long> {
    List<Myblog> findAllByOrderByModifiedAtDesc();
}
