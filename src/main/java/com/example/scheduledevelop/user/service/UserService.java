package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.comment.repository.CommentRepository;
import com.example.scheduledevelop.config.PasswordEncoder;
import com.example.scheduledevelop.global.exception.LoginFailedException;
import com.example.scheduledevelop.global.exception.UnauthorizedException;
import com.example.scheduledevelop.global.exception.UserNotFoundException;
import com.example.scheduledevelop.schedule.repsitory.ScheduleRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 회원가입
    @Transactional
    public SignupUserResponse save(SignupUserRequest request) {
        /* 요청 바디에있는 비밀번호를 가져와 암호화 실시 */
        String encodePassword = passwordEncoder.encode(request.getPassword());

        /* 암호화된 비밀번호로 유저 생성 */
        User user = new User(request.getUserName(), encodePassword, request.getEmail());

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
                () -> new UserNotFoundException("유저를 찾을 수 없습니다.")
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
            dtos = userRepository.findAll().stream()
                    .filter(user -> user.getUserName().equals(userName))
                    .map(user -> new GetUserResponse(
                            user.getId(),
                            user.getUserName(),
                            user.getEmail(),
                            user.getCreatedAt(),
                            user.getModifiedAt()
                    ))
                    .toList();
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
                () -> new UserNotFoundException("유저를 찾을 수 없습니다.")
        );

        // 로그인 유저와 일정을 생성한 유저가 동일한지 검증(인가 권한 체크)
        if (!user.getId().equals(sessionUser.getId())) {
            throw new UnauthorizedException("권한이 없습니다.");
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
                () -> new UserNotFoundException("유저를 찾을 수 없습니다.")
        );

        // 로그인 유저와 일정을 생성한 유저가 동일한지 검증(인가 권한 체크)
        if (!user.getId().equals(sessionUser.getId())) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        // 1. 유저가 작성한 댓글 삭제
        commentRepository.deleteByUser_Id(id);

        // 2. 유저가 작성한 일정에 달린 댓글 삭제
        commentRepository.deleteBySchedule_User_Id(id);

        // 3. 유저가 작성한 일정 삭제
        scheduleRepository.deleteByUser_Id(id);

        // 4. 유저 삭제
        userRepository.deleteById(id);
    }

    //로그인
    @Transactional
    public LoginResponse login(LoginRequest request) {

        // 로그인 이메일 검증
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UserNotFoundException("유저를 찾을 수 없습니다.")
        );

        /* 로그인 비밀번호 일치 검증, 평문 비밀번호와 암호화된 유저 비밀번호 매치 메서드를 통해 검증 */
        if (!getPasswordEncoder().matches(request.getPassword(),user.getPassword())) {
            throw new LoginFailedException("비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponse(user.getId(), user.getUserName(), user.getEmail());
    }
}
