package com.lalyns.board.service;

import javax.servlet.http.HttpServletRequest;

import com.lalyns.board.dto.request.CommentRequestDto;
import com.lalyns.board.dto.response.CommentResponseDto;

public interface CommentServiceInterface {
    CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, HttpServletRequest request);
    CommentResponseDto updateComment(Long postId, Long CommentId, CommentRequestDto requestDto, HttpServletRequest request);
    void deleteComment(Long postId, Long CommentId, HttpServletRequest request);
}
