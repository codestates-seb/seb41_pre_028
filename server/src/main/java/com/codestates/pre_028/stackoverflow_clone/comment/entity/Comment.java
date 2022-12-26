package com.codestates.pre_028.stackoverflow_clone.comment.entity;
//추후 주석제거

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Comment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(length = 65535, nullable = false)
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "QUESTION_ID")
//    private Question question;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

}
