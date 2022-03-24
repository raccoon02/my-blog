package com.sparta.myblog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Myblog extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    public Myblog(MyblogRequestDto requestDto){
        this.title = requestDto.getTitle().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n","<br>");
        this.name = requestDto.getName();
        this.text = requestDto.getText().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n","<br>");
    }

    public void update(MyblogRequestDto requestDto) {
        this.title = requestDto.getTitle().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n","<br>");
        this.text = requestDto.getText().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n","<br>");
    }
}
