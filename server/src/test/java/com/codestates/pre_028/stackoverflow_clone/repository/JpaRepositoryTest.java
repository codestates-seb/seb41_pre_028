package com.codestates.pre_028.stackoverflow_clone.repository;

import com.codestates.pre_028.stackoverflow_clone.Config.JpaConfig;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
public class JpaRepositoryTest {

    private final UserRepository userRepository;

    public JpaRepositoryTest(
            @Autowired UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        // Given

        // When
        List<User> user = userRepository.findAll();

        // Then
        assertThat(user)
                .hasSize(1);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        // Given
        long preCnt = userRepository.count();
        User user = new User("test1234", "email", "nickname", 0);

        // When
        userRepository.save(user);

        // Then
        assertThat(userRepository.count()).isEqualTo(preCnt + 1);
    }


    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine(){
        // Given
        User user = new User( "test1234", "email", "nickname", 0);
        String updatedNickname = "updateTest";
        user.setNickname(updatedNickname);

        // When
        User updateUserAccount = userRepository.saveAndFlush(user);

        // Then
        assertThat(updateUserAccount).hasFieldOrPropertyWithValue("nickname", updatedNickname);

    }



    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine(){
        // Given
        User user = new User( "test1234", "email", "nickname", 0);
        userRepository.save(user);
        long preUserAccountCnt = userRepository.count();

        // When
        userRepository.delete(user);

        // Then
        assertThat(userRepository.count()).isEqualTo(preUserAccountCnt - 1);
    }
}
