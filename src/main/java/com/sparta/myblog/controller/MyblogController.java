package com.sparta.myblog.controller;

import com.sparta.myblog.models.Myblog;
import com.sparta.myblog.dto.MyblogRequestDto;
import com.sparta.myblog.security.UserDetailsImpl;
import com.sparta.myblog.service.MyblogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class MyblogController {

    private final MyblogService myblogService;

    @PostMapping("/api/myblogs")
    public Myblog createBlog(@RequestBody MyblogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        requestDto.setName(userDetails.getUsername());
        return myblogService.createMyblog(requestDto);
    }

    @GetMapping("/api/myblogs")
    public List<Myblog> getMyblog() {return myblogService.getMyblog();}

    @GetMapping("/api/myblogs/detail")
    public ModelAndView getOneBlogAndComments(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myblogService.getOneBlogAndComments(id, userDetails);
    }
    @DeleteMapping("/api/myblogs/{id}")
    public Long deleteMyblog(@PathVariable Long id) {return myblogService.deleteMyblog(id);}
}
