package com.lalyns.board.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.lalyns.board.dto.request.LoginRequestDto;
import com.lalyns.board.dto.request.SignupRequestDto;
import com.lalyns.board.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public String signup(@RequestBody @Valid SignupRequestDto requestDto){
        userService.createUser(requestDto);
        return "account is registered.";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);
        return "login has successed";
    }
    
}
