package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 유저 관리 서비스
 * <p>
 * - 유저 CRUD 수행
 * - 세션 인증 정보를 활용하여 인가(권한) 검증 처리
 */

@Getter
@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public SignupUserResponse save(SignupUserRequest request) {
        User user = new User(request.getUserName(), request.getPassword(), request.getEmail());

        userRepository.save(user);
        return new SignupUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("유저를 찾을 수 없습니다.")
        );

        return new GetUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 전체 조회
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll(String userName) {
        List<GetUserResponse> dtos = new ArrayList<>();

        // 유저명이 있을 때 조건 조회
        if (userName != null) {
            for (User user : userRepository.findAll()) {
                if (user.getUserName().equals(userName)) {
                    GetUserResponse dto = new GetUserResponse(
                            user.getId(),
                            user.getUserName(),
                            user.getEmail(),
                            user.getCreatedAt(),
                            user.getModifiedAt()
                    );
                    dtos.add(dto);
                }
            }
        }

        // 유저명 없을 때 전체 조회
        else {
            for (User user : userRepository.findAll()) {
                GetUserResponse dto = new GetUserResponse(
                        user.getId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getModifiedAt()
                );
                dtos.add(dto);
            }
        }
        return dtos;
    }

    // 유저 수정
    @Transactional
    public UpdateUserResponse update(Long id, UpdateUserRequest request, SessionUser sessionUser) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("유저를 찾을 수 없습니다.")
        );

        // 로그인 유저와 일정을 생성한 유저가 동일한지 검증(인가 권한 체크)
        if (!user.getId().equals(sessionUser.getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }

        // 더티 체킹으로 유저 수정
        user.updateUser(request.getUserName(), request.getEmail());

        return new UpdateUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 삭제
    @Transactional
    public void delete(Long id, SessionUser sessionUser) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("유저를 찾을 수 없습니다.")
        );

        // 로그인 유저와 일정을 생성한 유저가 동일한지 검증(인가 권한 체크)
        if (!user.getId().equals(sessionUser.getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }
        userRepository.deleteById(id);
    }

    //로그인
    @Transactional
    public LoginResponse login(LoginRequest request) {

        // 로그인 이메일 검증
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("유저를 찾을 수 없습니다.")
        );

        // 로그인 비밀번호 일치 검증
        if (!request.getPassword().equals(user.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponse(user.getId(), user.getUserName(), user.getEmail());
    }
}
