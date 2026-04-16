package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
}
