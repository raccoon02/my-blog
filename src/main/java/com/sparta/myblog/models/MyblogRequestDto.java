package com.sparta.myblog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyblogRequestDto {
    private String title;
    private String name;
    private String text;
}
