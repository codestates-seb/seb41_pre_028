package com.codestates.pre_028.stackoverflow_clone.Question.entity;

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  private long questionId;
    @Column(nullable = false) private String title;
    @Column(nullable = false) private String content;
    @Column
    private String tag;


    public Question(String title,String content, String tag){

        this.title = title;
        this.content = content;
        this.tag = tag;
    }
}