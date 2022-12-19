package com.blog.blog.service;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.blog.dto.LoginRequestDto;
import com.blog.blog.dto.SignupRequestDto;
import com.blog.blog.entity.User;
import com.blog.blog.entity.UserRoleEnum;
import com.blog.blog.jwt.JwtUtil;
import com.blog.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    public final PasswordEncoder passwordEncoder;
    @Autowired
    public final UserRepository userRepository;
    
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto request) {
        String username = request.getUsername();
        String encodedPassword = hashingPassword(request.getPassword());
        String email = request.getEmail();

        UserRoleEnum role = UserRoleEnum.USER;
        if(request.isAdmin()) {
            if(!request.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능 합니다.");
            }
        }

        User user = new User(username, encodedPassword, email, role);
        userRepository.saveAndFlush(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto request, HttpServletResponse response) {
        String username = request.getUsername();
        String password = request.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!validationPassword(user, password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        };

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, 
                            jwtUtil.createToken(user.getUsername(), user.getRole()));
    }
    
    @Transactional
    public String hashingPassword(String password) {
        String encodePwd = passwordEncoder.encode(password);
        return encodePwd;
    }

    @Transactional
    public boolean validationPassword(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Transactional(readOnly = true)
    public User getUser(Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 ID 입니다.")
                );
    }
}
