package com.codestates.pre_028.stackoverflow_clone.User.repository;

import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}