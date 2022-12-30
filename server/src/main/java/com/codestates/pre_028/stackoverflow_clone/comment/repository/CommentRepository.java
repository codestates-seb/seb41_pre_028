package com.codestates.pre_028.stackoverflow_clone.comment.repository;

import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByAnswer_AnswerId(Long answerId);

    List<Comment> findByQuestion_QuestionId(Long questionId);
}
