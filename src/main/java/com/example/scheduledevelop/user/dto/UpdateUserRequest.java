package com.example.scheduledevelop.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter

public class UpdateUserRequest {
    private String userName;
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "올바른 이메일 형식이 아닙니다."
    )
    private String email;
}
