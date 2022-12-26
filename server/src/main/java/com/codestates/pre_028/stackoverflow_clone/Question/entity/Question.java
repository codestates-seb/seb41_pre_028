package com.codestates.pre_028.stackoverflow_clone.Question.entity;

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Question extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;
    @Column(nullable = false) private String title;
    @Column(nullable = false) private String content;
    @Column
    private String tag;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "question")
    List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Comment> comments = new ArrayList<>();

    public Question(String title,String content, String tag){

        this.title = title;
        this.content = content;
        this.tag = tag;
    }
    public void setComment(Comment comment){
        this.comments.add(comment);
        if(comment.getQuestion() != this){
            comment.setQuestion(this);
        }
    }
}