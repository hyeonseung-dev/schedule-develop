package com.example.scheduledevelop.schedule.entity;

import com.example.scheduledevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schduledevelops")
@NoArgsConstructor

public class ScheduleEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    // 연관관계 연동, FK는 null값을 가질수 없다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ScheduleEntity(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(String title, String content){
        this.title = title;
        this.content = content;

        // Base엔티티의 수정일을 변경해주는 메소드를 실행한다.
        changeModifiedAt();
    }
}
