package com.sparta.myblog.controller;

import com.sparta.myblog.models.Myblog;
import com.sparta.myblog.models.MyblogRepository;
import com.sparta.myblog.models.MyblogRequestDto;
import com.sparta.myblog.service.MyblogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyblogController {
    private final MyblogRepository myblogRepository;
    private final MyblogService myblogService;

    @PostMapping("/api/myblog")
    public Myblog createMyblog(@RequestBody MyblogRequestDto requestDto){
        Myblog myblog = new Myblog(requestDto);
        return  myblogRepository.save(myblog);
    }

    @GetMapping("/api/myblog")
    public List<Myblog> getMyblog(){
        return myblogRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/myblog/{id}")
    public Long deleteMyblog(@PathVariable Long id){
        myblogRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/myblog/{id}")
    public Long updateMyblog(@PathVariable Long id, @RequestBody MyblogRequestDto requestDto){
        return myblogService.update(id, requestDto);
    }
}
