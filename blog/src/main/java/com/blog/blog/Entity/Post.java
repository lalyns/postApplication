package com.blog.blog.Entity;

import com.blog.blog.Dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String contents;

    public Post(PostRequestDto requestDto) {
        this.subject = requestDto.getSubject();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void update(PostRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
