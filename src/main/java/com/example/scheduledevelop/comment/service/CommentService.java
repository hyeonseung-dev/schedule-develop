package com.example.scheduledevelop.comment.service;

import com.example.scheduledevelop.comment.dto.CreatCommentRequest;
import com.example.scheduledevelop.comment.dto.CreatCommentResponse;
import com.example.scheduledevelop.comment.dto.GetCommentResponse;
import com.example.scheduledevelop.comment.entity.Comment;
import com.example.scheduledevelop.comment.repository.CommentRepository;
import com.example.scheduledevelop.global.exception.ScheduleNotFoundException;
import com.example.scheduledevelop.global.exception.UserNotFoundException;
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

@Service
@Getter
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    /* 댓글 생성 */
    @Transactional
    public CreatCommentResponse save(CreatCommentRequest request, Long scheduleId, SessionUser sessionUser) {
        /*등록된 id 인지 확인*/
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(
                () -> new UserNotFoundException("등록된 id가 아닙니다.")
        );
        /*등록된 일정id 인지 확인*/
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("일정을 찾을 수 없습니다.")
        );

        /* 댓글 엔티티 생성 */
        Comment comment = new Comment(request.getContent(), schedule,user);

        /* 댓글 데이터 저장 */
        commentRepository.save(comment);

        return new CreatCommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getSchedule().getScheduleId(),
                comment.getUser().getId(),
                comment.getUser().getUserName(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAll(Long scheduleId) {
        /* 등록된 일정 id 인지 확인 */
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("일정을 찾을 수 없습니다.")
        );

        /* 해당 스케줄의 댓글 리스트 가져오기 */
        List<GetCommentResponse> dtos = commentRepository.findAllBySchedule_ScheduleId(scheduleId).stream()
                .map(comment -> new GetCommentResponse(
                        comment.getCommentId(),
                        comment.getContent(),
                        comment.getSchedule().getScheduleId(),
                        comment.getUser().getId(),
                        comment.getUser().getUserName(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                ))
                .toList();

        /* 반환 */
        return dtos;
    }
}
