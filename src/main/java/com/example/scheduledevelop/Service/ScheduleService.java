package com.example.scheduledevelop.Service;

import com.example.scheduledevelop.DTO.CreatscheduleRequest;
import com.example.scheduledevelop.DTO.CreatscheduleResponse;
import com.example.scheduledevelop.DTO.GetscheduleResponse;
import com.example.scheduledevelop.Entity.ScheduleEntity;
import com.example.scheduledevelop.Repsitory.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @Transactional
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

    // 일정 단건 조회
    @Transactional
    public GetscheduleResponse getOne(Long id) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("일정을 찾을 수 없습니다.")
        );

        return new GetscheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 전체 조회
    @Transactional
    public List<GetscheduleResponse> getAllOne(String authorName) {

        List<GetscheduleResponse> dtos= new ArrayList<>();

        // 작성자 명이 있을 때 조건 조회
        if(authorName != null){
            for(ScheduleEntity schedule : scheduleRepository.findAll()){
                if(schedule.getAuthorName().equals(authorName)){
                    GetscheduleResponse dto = new GetscheduleResponse(
                            schedule.getId(),
                            schedule.getTitle(),
                            schedule.getContent(),
                            schedule.getAuthorName(),
                            schedule.getCreatedAt(),
                            schedule.getModifiedAt());
                    dtos.add(dto);
                }
            }
        }

        // 작성자 명 없을 때 전체 조회
        else
        {
            for(ScheduleEntity schedule : scheduleRepository.findAll()){
                GetscheduleResponse dto = new GetscheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthorName(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt());
                dtos.add(dto);
            }
        }

        return dtos;

    }
}
