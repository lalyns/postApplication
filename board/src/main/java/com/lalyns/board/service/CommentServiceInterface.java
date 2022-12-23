package com.lalyns.board.service;

public interface CommentServiceInterface {
    CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, HttpServletRequest request);
}
