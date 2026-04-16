package com.example.scheduledevelop.Controller;

import com.example.scheduledevelop.Service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService scheduleService;
}
