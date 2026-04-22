package com.example.scheduledevelop.schedule.repsitory;

import com.example.scheduledevelop.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
    // 특정 유저가 작성한 일정 삭제
    void deleteByUser_Id(Long userId);
}
