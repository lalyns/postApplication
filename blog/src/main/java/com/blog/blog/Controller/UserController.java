package com.blog.blog.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.blog.dto.LoginRequestDto;
import com.blog.blog.dto.SignupRequestDto;
import com.blog.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public String signup(@RequestBody SignupRequestDto request) {
        userService.signup(request);
        return "success";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequestDto request, 
                                     HttpServletResponse response) {
        userService.login(request, response);
        return "success";
    }
}
