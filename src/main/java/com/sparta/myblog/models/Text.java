package com.sparta.myblog.models;

import com.sparta.myblog.dto.TextRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor//파라미터가 없는 기본 생성자를 생성
@Getter
@Entity
@Setter
public class Text extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long textId;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private  Long blogId;

    public Text(TextRequestDto requestDto, String nickname){
        this.text = requestDto.getText();
        this.blogId = requestDto.getBlogId();
        this.nickname = nickname;
    }

    public Long update (TextRequestDto requestDto){
        this.text = requestDto.getText();
        this.blogId = requestDto.getBlogId();
        return textId;
    }
}
