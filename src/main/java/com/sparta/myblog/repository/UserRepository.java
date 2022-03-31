package com.sparta.myblog.repository;

import com.sparta.myblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
    Optional<User> findByKakaoId(Long kakaoId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}
