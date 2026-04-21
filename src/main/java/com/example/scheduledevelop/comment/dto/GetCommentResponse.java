package com.example.scheduledevelop.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long commentId;
    private final String content;
    private final Long scheduleId;
    private final Long userId;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(Long commentId, String content, Long scheduleId, Long userId, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.content = content;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
