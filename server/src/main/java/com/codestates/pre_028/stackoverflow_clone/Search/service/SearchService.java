package com.codestates.pre_028.stackoverflow_clone.Search.service;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SearchService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Page<Question> searchQuestion(String value,Pageable pageable){
        if(value.matches("%5B[a-zA-Z0-9]%5D"))
            return questionRepository.findByTagContaining(value.replaceAll("[^\\w+]",""), pageable);
        else if(value.matches("\"[a-zA-Z0-9]\""))
            return questionRepository.findByTitleContaining(value.replaceAll("[^\\w+]", ""), pageable);

        return questionRepository.
                findByContentContaining(value.replaceAll("[^\\w+]", ""), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("questionId").descending()));

    }



/*
    @Transactional(readOnly = true)
    public Page<Question> searchQuestion(String keyword, Pageable pageable){

        return questionRepository.findByContentContaining(keyword, pageable);
    }*/
}
