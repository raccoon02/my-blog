package com.sparta.myblog.service;

import com.sparta.myblog.models.Myblog;
import com.sparta.myblog.models.MyblogRepository;
import com.sparta.myblog.models.MyblogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MyblogService {
    private final MyblogRepository myblogRepository;

    @Transactional
    public Long update(Long id, MyblogRequestDto requestDto) {
        Myblog myblog = myblogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        myblog.update(requestDto);
        return myblog.getId();
    }
}

