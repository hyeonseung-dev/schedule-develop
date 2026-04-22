package com.example.scheduledevelop.comment.repository;

import com.example.scheduledevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllBySchedule_ScheduleId(Long scheduleId);

    Long countBySchedule_ScheduleId(Long scheduleId);
    // 유저가 작성한 댓글 삭제
    void deleteByUser_Id(Long userId);

    // 특정 유저가 작성한 일정들에 달린 댓글 삭제
    void deleteBySchedule_User_Id(Long userId);

    void deleteBySchedule_ScheduleId(Long scheduleId);
}
