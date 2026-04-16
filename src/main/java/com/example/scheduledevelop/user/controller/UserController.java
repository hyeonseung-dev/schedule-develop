package com.example.scheduledevelop.user.controller;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.user.dto.CreateUserRequest;
import com.example.scheduledevelop.user.dto.CreateUserResponse;
import com.example.scheduledevelop.user.dto.GetUserResponse;
import com.example.scheduledevelop.user.service.UserService;
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

    // 유저 생성
    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 유저 단건 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<GetUserResponse> getOneUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(id));
    }



}
