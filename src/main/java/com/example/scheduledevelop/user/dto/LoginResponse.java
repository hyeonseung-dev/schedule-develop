package com.example.scheduledevelop.user.dto;

import lombok.Getter;

@Getter

public class LoginResponse {
    private final Long id;
    private final String userName;
    private final String email;

    public LoginResponse(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
