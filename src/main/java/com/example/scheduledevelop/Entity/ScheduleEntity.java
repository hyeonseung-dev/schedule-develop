package com.example.scheduledevelop.Entity;

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

    public ScheduleEntity(String title, String content, String authorName){
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }
}
