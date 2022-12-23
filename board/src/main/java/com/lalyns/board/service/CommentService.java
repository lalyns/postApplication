package com.lalyns.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lalyns.board.dto.request.CommentRequestDto;
import com.lalyns.board.dto.response.CommentResponseDto;
import com.lalyns.board.entity.Comment;
import com.lalyns.board.entity.Post;
import com.lalyns.board.entity.User;
import com.lalyns.board.repository.CommentRepository;
import com.lalyns.board.repository.PostRepository;
import com.lalyns.board.repository.UserRepository;
import com.lalyns.board.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentServiceInterface {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, HttpServletRequest request) {
        User user = getValidUserFromRequestToken(request);
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("post not found."));

        Comment comment = new Comment(post, user, requestDto.getContents());
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long postId, Long CommentId, CommentRequestDto requestDto,
            HttpServletRequest request) {
        User user = getValidUserFromRequestToken(request);
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("post not found."));

        Comment comment = commentRepository.findByIdAndPost_Id(CommentId, post.getId()).orElseThrow(
            ()-> new IllegalArgumentException("comment not found.")
        );
        
        if(!user.isAdmin() && !comment.isMatchedUserId(user.getId())) {
            throw new IllegalArgumentException("You do not have permission to edit comments.");
        }

        comment.updateComment(requestDto.getContents());

        return new CommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Long postId, Long CommentId, HttpServletRequest request) {
        User user = getValidUserFromRequestToken(request);
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("post not found."));

        Comment comment = commentRepository.findByIdAndPost_Id(CommentId, post.getId()).orElseThrow(
            ()-> new IllegalArgumentException("comment not found.")
        );
        
        if(!user.isAdmin() && !comment.isMatchedUserId(user.getId())) {
            throw new IllegalArgumentException("You do not have permission to edit comments.");
        }

        commentRepository.delete(comment);
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


}