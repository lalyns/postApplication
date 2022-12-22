package com.lalyns.board.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lalyns.board.dto.request.PostRequestDto;
import com.lalyns.board.dto.response.PostResponseDto;
import com.lalyns.board.entity.Post;
import com.lalyns.board.entity.User;
import com.lalyns.board.repository.PostRepository;
import com.lalyns.board.repository.UserRepository;
import com.lalyns.board.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements PostServiceInterface{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        User user = getValidUserFromRequestToken(request);

        if(user == null) throw new IllegalArgumentException("사용자가 존재하지 않습니다.");

        Post post = postRepository.saveAndFlush(new Post(requestDto, user));

        return new PostResponseDto(post);
    }

    @Override
    @Transactional
    public PostResponseDto getPost(Long id, HttpServletRequest request) {
        Post post = getValidPost(id, request);

        return new PostResponseDto(post);
    }

    @Override
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        Post post = getValidPost(id, request);
        User user = getValidUserFromRequestToken(request);

        if(!post.getUser().isAdmin() && !post.isMatchedUserId(user.getId())) 
            throw new IllegalArgumentException("작성자만 삭제/수정 할 수 있습니다.");

        post.update(requestDto);

        return new PostResponseDto(post);
    }

    @Override
    @Transactional
    public void deletePost(Long id, HttpServletRequest request) {
        Post post = getValidPost(id, request);
        User user = getValidUserFromRequestToken(request);

        if(!post.getUser().isAdmin() && !post.isMatchedUserId(user.getId())) 
            throw new IllegalArgumentException("작성자만 삭제/수정 할 수 있습니다.");
            
        postRepository.deleteById(post.getId());
    }
    
    @Transactional
    private User getValidUserFromRequestToken(HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null) {
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("token error.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("Invalid user.")
            );

            return user;
        }
        else {
            return null;
        }
    }

    @Transactional
    private Post getValidPost(Long id, HttpServletRequest request){
        Post post = postRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        
        return post;
    }

}
