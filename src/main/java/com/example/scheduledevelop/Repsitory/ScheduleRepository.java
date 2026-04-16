package com.example.scheduledevelop.Repsitory;

import com.example.scheduledevelop.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
}
