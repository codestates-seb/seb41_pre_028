package com.codestates.pre_028.stackoverflow_clone.Search.service;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SearchService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Page<Question> searchQuestion(String key, String value,Pageable pageable){
        switch(key){
            case "keyword" :
                    return questionRepository.findByContentContaining(value, pageable);
            case "title" :
                    return questionRepository.findByTitleContaining(value, pageable);
            case "tag" :
                    return questionRepository.findByTagContaining(value,pageable);
        }
        return questionRepository.findByContentContaining(value, pageable);
    }
/*
    @Transactional(readOnly = true)
    public Page<Question> searchQuestion(String keyword, Pageable pageable){

        return questionRepository.findByContentContaining(keyword, pageable);
    }*/
}
