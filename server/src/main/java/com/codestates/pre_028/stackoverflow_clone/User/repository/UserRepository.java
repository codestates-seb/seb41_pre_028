package com.codestates.pre_028.stackoverflow_clone.User.repository;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT n FROM User n WHERE n.nickname = :name")
    Optional<User> findByNickName(String name);
}