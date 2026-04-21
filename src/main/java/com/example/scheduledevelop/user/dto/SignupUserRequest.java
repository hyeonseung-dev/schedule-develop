package com.example.scheduledevelop.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

public class SignupUserRequest {
    private String userName;
    @Size(min = 9, message = "비밀번호는 {min}자 이상이어야 합니다")
    private String password;
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "올바른 이메일 형식이 아닙니다."
    )
    private String email;
}
