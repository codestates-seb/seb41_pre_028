package com.codestates.pre_028.stackoverflow_clone.Question.entity;

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long questionId;
    @Column(nullable = false) private String title;
    @Column(nullable = false) private String content;
    @Column
    private String tag;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "question" , cascade = CascadeType.ALL)
    List<Answer> answerList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "question" , cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
    }
    

    public void setComment(Comment comment){
        this.comments.add(comment);
        if(comment.getQuestion() != this){
            comment.setQuestion(this);
        }
    }

    public void setAnswer(Answer answer){
        this.answerList.add(answer);
        if (answer.getQuestion() != this){
            answer.setQuestion(this);

        }
    }
}