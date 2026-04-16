package com.example.scheduledevelop.Controller;

import com.example.scheduledevelop.DTO.*;
import com.example.scheduledevelop.Service.ScheduleService;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
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
    public ResponseEntity<CreatscheduleResponse> CreatSchedule(@RequestBody CreatscheduleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 일정 단건 조회
    @GetMapping("/schduledevelops/{id}")
    public ResponseEntity<GetscheduleResponse> GetOneSchedule(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(id));
    }

    // 일정 전체 조회
    @GetMapping("/schduledevelops")
    public ResponseEntity<List<GetscheduleResponse>> GetAllSchedule(@RequestParam(required = false) String authorName){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllOne(authorName));
    }

    // 일정 수정
    @PatchMapping("/schduledevelops/{id}")
    public ResponseEntity<UpdatescheduleResponse> UpdateSchedule(@PathVariable Long id, @RequestBody UpdatescheduleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(id, request));
    }

    // 일정 삭제
    @DeleteMapping("/schduledevelops/{id}")
    public ResponseEntity<Void> UpdateSchedule(@PathVariable Long id){
        scheduleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
