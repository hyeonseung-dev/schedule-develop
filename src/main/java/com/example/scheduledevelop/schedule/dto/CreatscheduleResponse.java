package com.example.scheduledevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CreatscheduleResponse {
    private final Long scheduleId;
    private final String title;
    private final String content;
    private final Long userId;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreatscheduleResponse(Long scheduleId, String title, String content, Long userId, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
