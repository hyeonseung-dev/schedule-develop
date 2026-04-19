package com.example.scheduledevelop.user.controller;

import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RestController
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    // 유저 생성, 비밀번호 검증 실행
    @PostMapping("/signup")
    public ResponseEntity<SignupUserResponse> createUser(@Valid @RequestBody SignupUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 로그인 기능
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request, HttpSession session){

        // 서비스계층에서 로그인 된 유저 엔티티를 받아온다.
        User user = userService.login(request);

        // 유저의 내부데이터를 세션유저DTO로 감싸준다.
        SessionUser sessionUser = new SessionUser(user.getId(),user.getEmail());

        // 세션메서드를 사용하여 로그인 유저의 세션키를 생성한다.
        session.setAttribute("SESSION_USER", sessionUser);

        // 유저의 정보를 반환 엔티티로 감싸준다.
        LoginResponse response = new LoginResponse(user.getId(),user.getUserName(),user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 유저 단건 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<GetUserResponse> getOneUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(id));
    }

    // 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAllUser(@RequestParam(required = false) String userName){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(userName));
    }

    // 유저 수정
    @PatchMapping("/users/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, request));
    }

    // 유저 삭제
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
