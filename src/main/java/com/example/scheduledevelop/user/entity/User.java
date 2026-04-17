package com.example.scheduledevelop.user.entity;

import com.example.scheduledevelop.user.dto.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor

public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String email;

    public User(String userName, String password,String email){
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public void updateUser(String userName, String email){
        this.userName = userName;
        this.email = email;

        // Base엔티티의 수정일을 변경해주는 메소드를 실행한다.
        changeModifiedAt();
    }

}
