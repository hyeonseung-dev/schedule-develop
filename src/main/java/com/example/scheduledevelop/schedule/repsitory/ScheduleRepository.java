package com.example.scheduledevelop.schedule.repsitory;

import com.example.scheduledevelop.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
}
