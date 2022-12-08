package com.blog.blog.Service;

import com.blog.blog.Entity.Post;
import com.blog.blog.Repository.PostRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.blog.Dto.PasswordRequestDto;
import com.blog.blog.Dto.PostRequestDto;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    public final PostRepository postRepository;

    @Autowired
    public final PasswordEncoder passwordEncoder;

    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        String encodePwd = encodePassword(post.getPassword());
        post.setPassword(encodePwd);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public String encodePassword(String password) {
        String encode = passwordEncoder.encode(password);
        return encode;
    }

    @Transactional
    public Boolean matchesPassword(Long id, String password) {
        Post post = getPost(id);
        return passwordEncoder.matches(password, post.getPassword());
    }

    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreateAtDesc();
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return postRepository.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("존재하지 않는 글 입니다.")
        );
    }

    @Transactional
    public Post update(Long id, PostRequestDto requestDto) {
        Post post = getPost(id);
        if (!matchesPassword(id, requestDto.getPassword()))
            return null;

        post.update(requestDto);
        return post;
    }

    @Transactional
    public Boolean deletePost(Long id, PasswordRequestDto password) {
        if (!matchesPassword(id, password.getPassword()))
            return false;

        postRepository.deleteById(id);
        return true;
    }


}
