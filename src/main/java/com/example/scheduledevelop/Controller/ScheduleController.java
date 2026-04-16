package com.example.scheduledevelop.Controller;

import com.example.scheduledevelop.DTO.CreatscheduleRequest;
import com.example.scheduledevelop.DTO.CreatscheduleResponse;
import com.example.scheduledevelop.DTO.GetscheduleResponse;
import com.example.scheduledevelop.Service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GetscheduleResponse> CreatSchedule(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(id));
    }

}
