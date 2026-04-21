package com.example.scheduledevelop.schedule.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

public class CreatscheduleRequest {
    @Size(max = 10, message = "제목은 10글자 이내여야합니다.")
    private String title;
    private String content;
}
