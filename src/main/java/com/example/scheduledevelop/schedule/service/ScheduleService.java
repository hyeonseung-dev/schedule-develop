package com.example.scheduledevelop.schedule.service;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.entity.ScheduleEntity;
import com.example.scheduledevelop.schedule.repsitory.ScheduleRepository;
import com.example.scheduledevelop.user.dto.SessionUser;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 일정 관리 서비스
 *
 * - 로그인 사용자 기반으로 일정 CRUD 수행
 * - 세션 인증 정보를 활용하여 인가(권한) 검증 처리
 */

@Getter
@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    // 유저 id로 유저 엔티티를 가져와야하기 때문에 유저레포지토리를 의존성 주입
    private final UserRepository userRepository;

    // 일정 생성
    @Transactional
    public CreatscheduleResponse save(CreatscheduleRequest request, SessionUser sessionUser) {

        /* 세션에 있는 유저아이디를 조회한다. */
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(
                () -> new IllegalStateException("아이디를 찾을 수 없습니다.")
        );

        ScheduleEntity schedule = new ScheduleEntity(request.getTitle(), request.getContent(), user);
        scheduleRepository.save(schedule);

        return new CreatscheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getUser().getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public GetscheduleResponse getOne(Long scheduleId) {
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정을 찾을 수 없습니다.")
        );

        return new GetscheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getUser().getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<GetscheduleResponse> getAllOne(String userName) {

        List<GetscheduleResponse> dtos = new ArrayList<>();

        // 작성자 명이 있을 때 조건 조회
        if (userName != null) {
            for (ScheduleEntity schedule : scheduleRepository.findAll()) {
                if (schedule.getUser().getUserName().equals(userName)) {
                    GetscheduleResponse dto = new GetscheduleResponse(
                            schedule.getScheduleId(),
                            schedule.getTitle(),
                            schedule.getContent(),
                            schedule.getUser().getId(),
                            schedule.getUser().getUserName(),
                            schedule.getCreatedAt(),
                            schedule.getModifiedAt());
                    dtos.add(dto);
                }
            }
        }

        // 작성자 명 없을 때 전체 조회
        else {
            for (ScheduleEntity schedule : scheduleRepository.findAll()) {
                GetscheduleResponse dto = new GetscheduleResponse(
                        schedule.getScheduleId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getUser().getId(),
                        schedule.getUser().getUserName(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt());
                dtos.add(dto);
            }
        }
        return dtos;
    }

    // 일정 수정
    @Transactional
    public UpdatescheduleResponse update(Long scheduleId, UpdatescheduleRequest request, SessionUser sessionUser) {

        // 일정 검증
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정을 찾을 수 없습니다.")
        );

        // 로그인 유저와 일정을 생성한 유저가 동일한지 검증(인가 권한 체크)
        if (!sessionUser.getId().equals(schedule.getUser().getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }

        // 더티 체킹으로 일정 수정
        schedule.updateSchedule(request.getTitle(), request.getContent());

        return new UpdatescheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getUser().getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId, SessionUser sessionUser) {
        // 일정 검증
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정을 찾을 수 없습니다.")
        );

        // 로그인 유저와 일정을 생성한 유저가 동일한지 검증(인가 권한 체크)
        if (!sessionUser.getId().equals(schedule.getUser().getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }

        scheduleRepository.deleteById(scheduleId);
    }
}
