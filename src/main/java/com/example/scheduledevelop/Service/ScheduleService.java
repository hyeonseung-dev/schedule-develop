package com.example.scheduledevelop.Service;

import com.example.scheduledevelop.DTO.CreatscheduleRequest;
import com.example.scheduledevelop.DTO.CreatscheduleResponse;
import com.example.scheduledevelop.Entity.ScheduleEntity;
import com.example.scheduledevelop.Repsitory.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public CreatscheduleResponse save(CreatscheduleRequest request) {

        ScheduleEntity schedule = new ScheduleEntity(request.getTitle(),request.getContent(),request.getAuthorName());
        scheduleRepository.save(schedule);

        return new CreatscheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
