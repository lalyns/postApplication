package com.blog.blog.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    String username;
    String password;
    String email;
    private boolean admin = false;
    private String adminToken = "";
}
