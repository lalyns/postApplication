package com.blog.blog.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.Dto.PasswordRequestDto;
import com.blog.blog.Dto.PostRequestDto;
import com.blog.blog.Entity.Post;
import com.blog.blog.Service.PostService;

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
    public Boolean deletePost(@PathVariable Long id, @RequestBody PasswordRequestDto requestDto) {
        return postService.deletePost(id, requestDto);
    }
}
