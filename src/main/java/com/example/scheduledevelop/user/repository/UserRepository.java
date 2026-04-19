package com.example.scheduledevelop.user.repository;

import com.example.scheduledevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // 이메일로 유저를 찾는 스프링 쿼리 메서드 구현, 널값을 처리하기 위한 옵셔널 사용
    Optional<User> findByEmail(String email);
}
