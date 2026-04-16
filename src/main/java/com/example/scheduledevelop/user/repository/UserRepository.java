package com.example.scheduledevelop.user.repository;

import com.example.scheduledevelop.user.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user,Long> {
}
