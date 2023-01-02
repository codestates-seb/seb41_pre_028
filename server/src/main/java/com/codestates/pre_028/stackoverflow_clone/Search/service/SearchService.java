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
        if(value.matches("\\[[\\w|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+\\]"))
            return questionRepository.findByTagContaining(value.replaceAll("[^[\\w+ㄱ-ㅎ|ㅏ-ㅣ|가-힣+]+]",""), pageable);
        else if(value.matches("\"[\\w|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+\""))
            return questionRepository.findByTitleContaining(value.replaceAll("[^[\\w+ㄱ-ㅎ|ㅏ-ㅣ|가-힣+]+]", ""), pageable);

        return questionRepository.
                findByContentContaining(value.replaceAll("[^[\\w+ㄱ-ㅎ|ㅏ-ㅣ|가-힣+]+]", ""), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("questionId").descending()));

    }



/*
    @Transactional(readOnly = true)
    public Page<Question> searchQuestion(String keyword, Pageable pageable){

        return questionRepository.findByContentContaining(keyword, pageable);
    }*/
}
