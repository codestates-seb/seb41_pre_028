package com.codestates.pre_028.stackoverflow_clone.Question.repository;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.QQuestion;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.querydsl.QuestionRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;


public interface QuestionRepository extends JpaRepository<Question, Long>,
        QuestionRepositoryCustom,
        QuerydslPredicateExecutor<Question>,
        QuerydslBinderCustomizer<QQuestion>
{
    Page<Question> findByTitleContaining(String title, Pageable pageable);
    Page<Question> findByContentContaining(String content, Pageable pageable);

    Page<Question> findByTagContaining(String tag, Pageable pageable);

    @Override
    default void customize(QuerydslBindings bindings , QQuestion root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title,root.content,root.tag,root.createdAt,root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like 'ㄱ '
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like ' '
        bindings.bind(root.tag).first(StringExpression::containsIgnoreCase); // like ' '
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // like ' '
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like ' '
    }
}


