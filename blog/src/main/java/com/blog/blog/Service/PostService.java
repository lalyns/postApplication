package com.blog.blog.service;

import com.blog.blog.dto.PasswordRequestDto;
import com.blog.blog.dto.PostRequestDto;
import com.blog.blog.entity.Post;
import com.blog.blog.entity.User;
import com.blog.blog.jwt.JwtUtil;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.UserRepository;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    public final PostRepository postRepository;
    public final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public Post createPost(PostRequestDto requestDto,
                            HttpServletRequest request) {
        
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        
        if (token != null) {
            if  (jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("token error!!");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow
            (
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            
            Post post = postRepository.saveAndFlush(new Post(requestDto, user.getUsername()));
            
            return post;
        } else {
            return null;
        }

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

        post.update(requestDto);
        return post;
    }

    @Transactional
    public Boolean deletePost(Long id, PasswordRequestDto password) {

        postRepository.deleteById(id);
        return true;
    }


}
