package com.codestates.pre_028.stackoverflow_clone.comment.repository;

import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
