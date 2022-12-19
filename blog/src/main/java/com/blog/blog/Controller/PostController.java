package com.blog.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.blog.blog.dto.PasswordRequestDto;
import com.blog.blog.dto.PostRequestDto;
import com.blog.blog.entity.Post;
import com.blog.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/posts")
    @ResponseBody
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/api/posts/{id}")
    @ResponseBody
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/api/posts/{id}")
    @ResponseBody
    public Post updatePost(
        @PathVariable Long id,
        @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public Boolean deletePost(  @PathVariable Long id,
                                @RequestBody PasswordRequestDto requestDto) {
        return postService.deletePost(id, requestDto);
    }
}
