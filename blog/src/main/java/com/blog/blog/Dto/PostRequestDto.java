package com.blog.blog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String subject;
    private String username;
    private String contents;
    private String password;
}
