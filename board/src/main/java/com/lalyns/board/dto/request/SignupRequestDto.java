package com.lalyns.board.dto.request;

import javax.validation.constraints.Pattern;

import com.lalyns.board.entity.enums.UserRoleEnum;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{4,10}$", message = "아이디는 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@!%*#?&])[A-Za-z0-9$@!%*#?&]{8,15}$",
             message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자를 포함하여야 합니다")
    private String password;

    private final UserRoleEnum role = UserRoleEnum.USER;
}
