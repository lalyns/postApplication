package com.lalyns.board.service;

import javax.servlet.http.HttpServletResponse;

import com.lalyns.board.dto.request.LoginRequestDto;
import com.lalyns.board.dto.request.SignupRequestDto;

public interface UserServiceInterface {
    void createUser(SignupRequestDto requestDto);
    void login(LoginRequestDto requestDto, HttpServletResponse response);
}
