package com.codestates.pre_028.stackoverflow_clone.Vote.entity;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class VoteQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteQuestionId;

    private Long voteNum = 0L;

    @OneToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ElementCollection
    private List<Long> questionUserIds = new ArrayList<>();

    public void setQuestionUserIds(Long userId){
        this.questionUserIds.add(userId);
    }

}
