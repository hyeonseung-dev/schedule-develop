package com.example.scheduledevelop.comment.entity;

import com.example.scheduledevelop.schedule.entity.ScheduleEntity;
import com.example.scheduledevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "comments")
@Getter
@NoArgsConstructor

public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String content;

    // 연관관계 연동, FK는 null값을 가질수 없다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;

    // 연관관계 연동, FK는 null값을 가질수 없다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(String content, ScheduleEntity schedule, User user){
        this.content = content;
        this.schedule = schedule;
        this.user = user;
    }

    public void updateSchedule(String content){
        this.content = content;

        /* Base엔티티의 수정일을 변경해주는 메소드를 실행한다. */
        changeModifiedAt();
    }
}
