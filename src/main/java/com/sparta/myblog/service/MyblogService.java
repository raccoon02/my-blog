package com.sparta.myblog.service;

import com.sparta.myblog.dto.MyblogRequestDto;
import com.sparta.myblog.models.Myblog;
import com.sparta.myblog.models.Text;
import com.sparta.myblog.repository.MyblogRepository;
import com.sparta.myblog.repository.TextRepository;
import com.sparta.myblog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyblogService {
    private  final MyblogRepository myblogRepository;
    private  final TextRepository textRepository;



    public Myblog createMyblog(MyblogRequestDto requestDto){
        return myblogRepository.save(new Myblog(requestDto));
    }
    public List<Myblog> getMyblog(){
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        return myblogRepository.findAllByModifiedAtBetweenOrderByCreatedAtDesc(start,end);
    }

    public Long deleteMyblog(Long id) {
        myblogRepository.deleteById(id);
        return id;
    }

    public ModelAndView getOneBlogAndComments(Long id, UserDetailsImpl userDetails) {

        Myblog myblog = myblogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("")
        );
        List<Text> text = textRepository.findAllByBlogIdOrderByCreatedAtDesc(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("post"); // 뷰의 이름
        mv.addObject("data", myblog);
        mv.addObject("commentList", text);
        if (userDetails != null) {
            mv.addObject("user", userDetails.getUser().getNickname());
        } else
            mv.addObject("user", "visitor");
        return mv;
    }
}

