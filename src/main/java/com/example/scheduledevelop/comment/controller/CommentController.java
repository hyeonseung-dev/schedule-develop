package com.example.scheduledevelop.comment.controller;

import com.example.scheduledevelop.comment.dto.CreatCommentRequest;
import com.example.scheduledevelop.comment.dto.CreatCommentResponse;
import com.example.scheduledevelop.comment.service.CommentService;
import com.example.scheduledevelop.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

public class CommentController {
    private final CommentService commentService;

    /*댓글 생성*/
    @PostMapping("/schduledevelops/{scheduleId}/comments")
    public ResponseEntity<CreatCommentResponse> CreatComment(
            // 로그인 세션 확인
            @SessionAttribute(name = "SESSION_USER", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId,
            @RequestBody CreatCommentRequest request) {

        // 세션이 널값이면 인증필요 상태코드 반환
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request,scheduleId, sessionUser));
    }
}
