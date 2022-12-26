package com.codestates.pre_028.stackoverflow_clone.answer.repository;

import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
