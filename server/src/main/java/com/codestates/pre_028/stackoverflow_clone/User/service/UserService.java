package com.codestates.pre_028.stackoverflow_clone.User.service;

import com.codestates.pre_028.stackoverflow_clone.Auth.utils.CustomAuthorityUtils;
import com.codestates.pre_028.stackoverflow_clone.Auth.utils.UserRegistrationApplicationEvent;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final ApplicationEventPublisher publisher;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CustomAuthorityUtils customAuthorityUtils,
            ApplicationEventPublisher publisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customAuthorityUtils = customAuthorityUtils;
        this.publisher = publisher;
    }


    //유저 단일 조회
    @Transactional(readOnly = true)
    public User findUser(long id){
        return findVerifiedUser(id);
    }
    //유저 전체 조회
    public List<User> findUsers(){
        return new ArrayList<>(userRepository.findAll());
    }
    //유저 검증
    @Transactional(readOnly = true)
    public User findVerifiedUser(long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    //유저 등록
    public User createUser(User user){
        Optional<User> verifiedUser = userRepository.findByEmail(user.getEmail());
        if(verifiedUser.isPresent())
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXISTS);
        Optional<User> verifiedNickname = userRepository.findByNickName(user.getNickname());
        if(verifiedNickname.isPresent())
            throw new BusinessLogicException(ExceptionCode.NICKNAME_EXISTS);

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        List<String> roles = customAuthorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        publisher.publishEvent(new UserRegistrationApplicationEvent(savedUser));
        return savedUser;
    }

    // 유저 수정

    public User updateUser(User user, long id){
        User verifiedUser = findVerifiedUser(id);
        verifiedUser.setEmail(user.getEmail());
        verifiedUser.setNickname(user.getNickname());
        return userRepository.save(verifiedUser);
    }


    //유저 삭제
    public void deleteUser(Long id){
        User verifiedUser = findVerifiedUser(id);
        userRepository.deleteById(id);
    }

    public User getLoginUserWithToken() { // 로그인된 유저 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser"))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);

        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        User user = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));


        System.out.println("HERE:"+user.getUserId());

        return user;
    }
}

