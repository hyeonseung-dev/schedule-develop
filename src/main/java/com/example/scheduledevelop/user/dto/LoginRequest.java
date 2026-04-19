package com.example.scheduledevelop.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

public class LoginRequest {
    private String email;
    @Size(min = 9, message = "비밀번호는 {min}자 이상이어야 합니다")
    private String password;
}
