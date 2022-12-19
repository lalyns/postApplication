package com.blog.blog.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String createAt;
    private String subject;
    private String username;
    private String contents;

    public PostResponseDto()
    {

    }
}
