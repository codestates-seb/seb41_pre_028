package com.codestates.pre_028.stackoverflow_clone.User.entity;

import com.codestates.pre_028.stackoverflow_clone.Auditing.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString(callSuper = true)
@Entity
@Table(name="USERS")
public class User extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userId;
    @Setter @Column(nullable = false) private String nickname;
    @Setter @Column(nullable = false) private String email;
    @Setter @Column(nullable = false) private String password;
    @Setter @Column private Integer reputation;


    public User(Long userId, String password, String email, String nickname, Integer reputation) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.reputation = reputation;
    }
}
