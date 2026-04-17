package com.example.scheduledevelop.user.controller;

import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.service.UserService;
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
