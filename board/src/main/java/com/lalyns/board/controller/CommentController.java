package com.lalyns.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.lalyns.board.dto.request.CommentRequestDto;
import com.lalyns.board.dto.response.CommentResponseDto;
import com.lalyns.board.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    @ResponseBody
    public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.createComment(postId, requestDto, request);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    @ResponseBody
    public CommentResponseDto updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.updateComment(postId, commentId, requestDto, request);
    }

    @DeleteMapping("/post/{postId}/comments/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId, HttpServletRequest request){
        commentService.deleteComment(postId, commentId, request);
        return "comment has deleted.";
    }
}
