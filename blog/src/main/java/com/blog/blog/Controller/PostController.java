package com.blog.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.blog.blog.dto.PostRequestDto;
import com.blog.blog.entity.Post;
import com.blog.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/posting")
    public Post createPost(@RequestBody PostRequestDto requestDto,
                            HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/index")
    @ResponseBody
    public List<Post> getPosts(HttpServletRequest request) {
        return postService.getPosts(request);
    }

    @GetMapping("/index/{id}")
    @ResponseBody
    public Post getPost(@PathVariable Long id, HttpServletRequest request) {
        return postService.getPost(id, request);
    }

    @PutMapping("/index/{id}")
    @ResponseBody
    public Post updatePost(
        @PathVariable Long id,
        @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.update(id, requestDto, request);
    }

    @DeleteMapping("/index/{id}")
    public Boolean deletePost(  @PathVariable Long id,
                                 HttpServletRequest request) {
        return postService.deletePost(id, request);
    }
}
