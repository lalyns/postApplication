package com.blog.blog.entity;

import com.blog.blog.dto.PostRequestDto;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    public Post(PostRequestDto requestDto, String username) {
        this.subject = requestDto.getSubject();
        this.username = username;
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
