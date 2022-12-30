package com.lalyns.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lalyns.board.dto.request.PostRequestDto;
import com.lalyns.board.dto.response.PostResponseDto;
import com.lalyns.board.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @PostMapping("/posts")
    @ResponseBody
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public PostResponseDto getPost(@PathVariable Long id, HttpServletRequest request) {
        return postService.getPost(id, request);
    }

    @PutMapping("/posts/{id}")
    @ResponseBody
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id, HttpServletRequest request) {
        postService.deletePost(id, request);
        return "Delete Posting Succeed";
    }
    
}
