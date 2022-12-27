package com.codestates.pre_028.stackoverflow_clone.Question.repository.querydsl;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.QQuestion;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class QuestionRepositoryCustomImpl extends QuerydslRepositorySupport implements QuestionRepositoryCustom  {

    public QuestionRepositoryCustomImpl(){
        super(Question.class);}

    @Override
    public List<String> findAllDistinctContent(){
        QQuestion question = QQuestion.question;

        return from(question)
                .distinct()
                .select(question.content)
                .where(question.content.isNotNull())
                .fetch();

    }
}
