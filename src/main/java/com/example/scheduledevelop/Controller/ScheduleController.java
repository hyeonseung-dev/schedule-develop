package com.example.scheduledevelop.Controller;

import com.example.scheduledevelop.DTO.CreatscheduleRequest;
import com.example.scheduledevelop.DTO.CreatscheduleResponse;
import com.example.scheduledevelop.Service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
