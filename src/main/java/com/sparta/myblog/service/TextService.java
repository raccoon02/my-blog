package com.sparta.myblog.service;

import com.sparta.myblog.dto.TextRequestDto;
import com.sparta.myblog.models.Text;
import com.sparta.myblog.repository.TextRepository;
import com.sparta.myblog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TextService {

    private final TextRepository textRepository;

    @Transactional
    public Long update(Long id, TextRequestDto requestDto){
        Text text = textRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        return text.update(requestDto);
    }
    public Long deleteText(Long id){
        textRepository.deleteById(id);
        return id;
    }

    public Text createText(TextRequestDto requestDto, UserDetailsImpl userDetails){
        Text text = new Text(requestDto, userDetails.getUser().getNickname());
        return textRepository.save(text);
    }
}
