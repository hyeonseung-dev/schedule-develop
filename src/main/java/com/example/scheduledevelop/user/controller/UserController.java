package com.example.scheduledevelop.user.controller;

import com.example.scheduledevelop.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
}
