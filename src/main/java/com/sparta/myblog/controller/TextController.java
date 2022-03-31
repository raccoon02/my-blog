package com.sparta.myblog.controller;

import com.sparta.myblog.dto.TextRequestDto;
import com.sparta.myblog.models.Text;
import com.sparta.myblog.security.UserDetailsImpl;
import com.sparta.myblog.service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TextController {

    private final TextService textService;

    @PostMapping("/api/texts")
    public Text createText(@RequestBody TextRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return textService.createText(requestDto, userDetails);
    }
    @DeleteMapping("/api/texts/{id}")
    public Long deleteText(@PathVariable Long id) {
        return textService.deleteText(id);
    }

    @PutMapping("/api/texts/{id}")
    public Long updateText(@PathVariable Long id, @RequestBody TextRequestDto requestDto){
        return textService.update(id, requestDto);
    }
}
