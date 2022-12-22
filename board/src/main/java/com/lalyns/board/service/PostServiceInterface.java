package com.lalyns.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lalyns.board.dto.request.PostRequestDto;
import com.lalyns.board.dto.response.PostResponseDto;

public interface PostServiceInterface {
    List<PostResponseDto> getPosts();
    PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request);
    PostResponseDto getPost(Long id, HttpServletRequest request);
    PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request);
    void deletePost(Long id, HttpServletRequest request);
}
