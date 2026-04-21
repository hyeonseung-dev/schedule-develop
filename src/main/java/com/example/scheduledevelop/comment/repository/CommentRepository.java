package com.example.scheduledevelop.comment.repository;

import com.example.scheduledevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllBySchedule_ScheduleId(Long scheduleId);
}
