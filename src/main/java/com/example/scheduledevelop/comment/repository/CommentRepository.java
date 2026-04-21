package com.example.scheduledevelop.comment.repository;

import com.example.scheduledevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
