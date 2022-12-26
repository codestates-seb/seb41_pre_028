package com.codestates.pre_028.stackoverflow_clone.Question.repository;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}