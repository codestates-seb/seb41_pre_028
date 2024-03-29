package com.codestates.pre_028.stackoverflow_clone.answer.entity;

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.Vote.entity.VoteAnswer;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 65535, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_NORMAL;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @JsonManagedReference
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "answer", cascade = CascadeType.ALL)
    private VoteAnswer voteAnswer;

    public void setVoteAnswer(VoteAnswer voteAnswer){
        this.voteAnswer = voteAnswer;
        if(voteAnswer.getAnswer() != this){
            voteAnswer.setAnswer(this);
        }
    }

    public void setComment(Comment comment){
        this.comments.add(comment);
        if(comment.getAnswer() != this){
            comment.setAnswer(this);
        }
    }

    public enum AnswerStatus{
        ANSWER_NORMAL("일반 답변"),
        ANSWER_ADOPTED("채택된 답변"),
        ANSWER_DELETED("삭제된 답변");

        @Getter
        private final String status;

        AnswerStatus( String status){
            this.status = status;
        }
    }
}
