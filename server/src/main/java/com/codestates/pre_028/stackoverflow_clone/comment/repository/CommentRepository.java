package com.codestates.pre_028.stackoverflow_clone.comment.repository;

import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByAnswerId(Long AnswerId, Pageable pageable);
}