package com.example.scheduledevelop.schedule.controller;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.service.ScheduleService;
import com.example.scheduledevelop.user.dto.SessionUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RestController
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schduledevelops")
    public ResponseEntity<CreatscheduleResponse> CreatSchedule(
            // 로그인 세션 확인
            @SessionAttribute(name = "SESSION_USER",required = false) SessionUser sessionUser,
            @RequestBody CreatscheduleRequest request){

        // 세션이 널값이면 인증필요 상태코드 반환
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request,sessionUser));
    }

    // 일정 단건 조회
    @GetMapping("/schduledevelops/{scheduleId}")
    public ResponseEntity<GetscheduleResponse> GetOneSchedule(@PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    // 일정 전체 조회
    @GetMapping("/schduledevelops")
    public ResponseEntity<List<GetscheduleResponse>> GetAllSchedule(@RequestParam(required = false) String userName){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllOne(userName));
    }

    // 일정 수정
    @PatchMapping("/schduledevelops/{scheduleId}")
    public ResponseEntity<UpdatescheduleResponse> UpdateSchedule(@PathVariable Long scheduleId, @RequestBody UpdatescheduleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/schduledevelops/{id}")
    public ResponseEntity<Void> UpdateSchedule(@PathVariable Long id){
        scheduleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
