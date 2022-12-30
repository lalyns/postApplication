package com.lalyns.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.lalyns.board.dto.request.SignupRequestDto;
import com.lalyns.board.entity.enums.UserRoleEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private final List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Comment> comments = new ArrayList<>();

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(SignupRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.role = requestDto.getRole();
    }

    public boolean isValidPassword(String password) {
        return this.password.equals(password);
    }

    public boolean isAdmin() {
        return this.role == UserRoleEnum.ADMIN;
    }
}
