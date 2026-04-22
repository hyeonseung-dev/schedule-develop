package com.example.scheduledevelop.schedule.controller;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.service.ScheduleService;
import com.example.scheduledevelop.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schduledevelops")
    public ResponseEntity<CreatscheduleResponse> CreatSchedule(
            // 로그인 세션 확인
            @SessionAttribute(name = "SESSION_USER", required = false) SessionUser sessionUser,
            @RequestBody CreatscheduleRequest request) {

        // 세션이 널값이면 인증필요 상태코드 반환
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request, sessionUser));
    }

    // 일정 단건 조회
    @GetMapping("/schduledevelops/{scheduleId}")
    public ResponseEntity<GetscheduleResponse> GetOneSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    // 일정 전체 조회
    @GetMapping("/schduledevelops")
    public ResponseEntity<List<GetscheduleResponse>> GetAllSchedule(@RequestParam(required = false) String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllOne(userName));
    }

    // 일정 수정
    @PatchMapping("/schduledevelops/{scheduleId}")
    public ResponseEntity<UpdatescheduleResponse> UpdateSchedule(
            @PathVariable Long scheduleId,
            @SessionAttribute(name = "SESSION_USER", required = false) SessionUser sessionUser,
            @RequestBody UpdatescheduleRequest request
    ) {

        // 세션이 널값이면 인증필요 상태코드 반환
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request, sessionUser));
    }

    // 일정 삭제
    @DeleteMapping("/schduledevelops/{scheduleId}")
    public ResponseEntity<Void> UpdateSchedule(
            @PathVariable Long scheduleId,
            @SessionAttribute(name = "SESSION_USER", required = false) SessionUser sessionUser
    ) {

        scheduleService.delete(scheduleId, sessionUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 일정 목록을 페이지 단위로 조회한다.
     * 기본값은 page=0, size=10이며, 클라이언트 요청에 따라 변경 가능하다.
     *
     * @param page 조회할 페이지 번호 (0부터 시작)
     * @param size 한 페이지당 조회할 데이터 개수
     * @return 페이징된 일정 목록
     */
    @GetMapping("/page")
    public ResponseEntity<Page<GetschedulePageResponse>> GetPageSchedule(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedules(page, size));
    }

}
