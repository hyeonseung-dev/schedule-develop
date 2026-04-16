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
    private String authorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public ScheduleEntity(String title, String content, String authorName){
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }

    public void updateSchedule(String title, String content){
        this.title = title;
        this.content = content;

        // Base엔티티의 수정일을 변경해주는 메소드를 실행한다.
        changeModifiedAt();
    }
}
