package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.user.dto.CreateUserRequest;
import com.example.scheduledevelop.user.dto.CreateUserResponse;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    // 유저 생성
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(request.getUserName(),request.getEmail());

        userRepository.save(user);
        return new CreateUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
