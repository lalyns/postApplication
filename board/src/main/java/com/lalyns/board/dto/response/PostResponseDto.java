package com.lalyns.board.dto.response;

import java.time.LocalDateTime;

import com.lalyns.board.entity.Post;

import lombok.Getter;

@Getter
public class PostResponseDto {
    private final String title;
    private final String contents;
    private final LocalDateTime modifiedAt;

    private final String username;

    public PostResponseDto(Post post){
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.modifiedAt = post.getModifiedAt();
        this.username = post.getUser().getUsername();
    }
}
