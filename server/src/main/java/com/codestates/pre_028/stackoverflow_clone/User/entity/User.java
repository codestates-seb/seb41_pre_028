package com.codestates.pre_028.stackoverflow_clone.User.entity;

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name="USERS")
public class User extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userId;
    @Column(nullable = false) private String nickname;
    @Column(nullable = false) private String email;
    @Column(nullable = false) private String password;
    @Column private Integer reputation;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)

    private List<Answer> answers = new ArrayList<>();

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    public User( String password, String email, String nickname, Integer reputation) {

        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.reputation = reputation;
    }

    public User(Long userId, String nickname, String email, String password,
                List<String> roles, List<Question> questions,
                List<Answer> answers, List<Comment> comments, int reputation){
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.questions = questions;
        this.answers = answers;
        this.comments = comments;
        this.reputation = reputation;
    }


}
