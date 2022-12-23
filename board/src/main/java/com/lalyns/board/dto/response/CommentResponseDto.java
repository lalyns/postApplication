package com.lalyns.board.dto.response;

import java.time.LocalDateTime;

import com.lalyns.board.entity.Comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final Long postId;
    private final String username;
    private final String contents;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.postId = comment.getPost().getId();
        this.username = comment.getUser().getUsername();
        this.contents = comment.getContents();
        this.modifiedAt = comment.getModifiedAt();
    }
}
