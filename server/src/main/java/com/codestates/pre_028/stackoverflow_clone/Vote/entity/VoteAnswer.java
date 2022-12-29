package com.codestates.pre_028.stackoverflow_clone.Vote.entity;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class VoteAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteAnswerId;

    private Long voteNum = 0L;

    @OneToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @ElementCollection
    private List<Long> answerUserIds = new ArrayList<>();

    public void setAnswerUserIds(Long userId){
        this.answerUserIds.add(userId);
    }
}
