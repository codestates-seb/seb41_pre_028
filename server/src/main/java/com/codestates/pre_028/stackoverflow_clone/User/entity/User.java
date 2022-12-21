package com.codestates.pre_028.stackoverflow_clone.User.entity;

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    public User( String password, String email, String nickname, Integer reputation) {

        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.reputation = reputation;
    }
}
