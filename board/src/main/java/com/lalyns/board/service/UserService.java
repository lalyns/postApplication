package com.lalyns.board.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lalyns.board.dto.request.LoginRequestDto;
import com.lalyns.board.dto.request.SignupRequestDto;
import com.lalyns.board.entity.User;
import com.lalyns.board.repository.UserRepository;
import com.lalyns.board.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void createUser(SignupRequestDto requestDto) {
        boolean isExist = userRepository.existsByUsername(requestDto.getUsername());

        if(isExist) throw new IllegalArgumentException("중복된 Id가 있습니다.");

        User user = new User(requestDto);
        
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
            () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if(!user.isValidPassword(user.getPassword())) 
            throw new IllegalArgumentException("비밀 번호가 일치하지 않습니다.");

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }

}
