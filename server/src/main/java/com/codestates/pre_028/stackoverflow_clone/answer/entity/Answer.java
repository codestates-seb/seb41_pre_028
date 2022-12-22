package com.codestates.pre_028.stackoverflow_clone.answer.entity;
//추후주석제거
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
//import com.codestates.pre_028.stackoverflow_clone.user.entity.User;
//import com.codestates.pre_028.stackoverflow_clone.question.entity.Qusetion;
import com.codestates.pre_028.stackoverflow_clone.audit.Auditable;
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
public class Answer extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 65535, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_NORMAL;

//    @ManyToOne
//    @JoinColumn(name = "QUESTION_ID")
//    private Question question;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

    public void setComment(Comment comment){
        this.comments.add(comment);
        if(comment.getAnswer() != this){
            comment.setAnswer(this);
        }
    }

    public enum AnswerStatus{
        ANSWER_DELETED(0, "삭제된 답변"),
        ANSWER_ADOPTED(1, "채택된 답변"),
        ANSWER_NORMAL(2, "일반 답변");

        private int statusNumber;
        private String statusDescription;

        AnswerStatus(int statusNumber, String statusDescription){
            this.statusNumber = statusNumber;
            this.statusDescription = statusDescription;
        }
    }
}
