package com.codestates.pre_028.stackoverflow_clone.Question.repository.querydsl;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<String> findAllDistinctContent();
}
