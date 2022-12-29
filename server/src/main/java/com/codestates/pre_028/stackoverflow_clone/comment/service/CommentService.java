package com.codestates.pre_028.stackoverflow_clone.comment.service;

import com.codestates.pre_028.stackoverflow_clone.Question.entity.Question;
import com.codestates.pre_028.stackoverflow_clone.Question.repository.QuestionRepository;
import com.codestates.pre_028.stackoverflow_clone.User.entity.User;
import com.codestates.pre_028.stackoverflow_clone.User.repository.UserRepository;
import com.codestates.pre_028.stackoverflow_clone.answer.entity.Answer;
import com.codestates.pre_028.stackoverflow_clone.answer.repository.AnswerRepository;
import com.codestates.pre_028.stackoverflow_clone.comment.dto.CommentResponseDto;
import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.codestates.pre_028.stackoverflow_clone.comment.repository.CommentRepository;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> searchCommentWithAnswer(long answerId){
        return commentRepository.findByAnswer_AnswerId(answerId)
                .stream()
                .map(comment->CommentResponseDto
                        .builder()
                        .userId(comment.getUser().getUserId())
                        .build())
                .collect(Collectors.toList());

    }

    public Comment createAnswerComment(Comment comment){
        Answer answer = answerRepository.getReferenceById(comment.getAnswer().getAnswerId());
        User user = userRepository.getReferenceById(comment.getUser().getUserId());


        comment.setAnswer(answer);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        return savedComment;
    }

    public Comment createQuestionComment(Comment comment){
        Question question = questionRepository.getReferenceById(comment.getQuestion().getQuestionId());
        User user = userRepository.getReferenceById(comment.getUser().getUserId());


        comment.setQuestion(question);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        return savedComment;
    }


    public Comment updateComment(Comment comment){
        Comment findComment = findVerifiedComment(comment.getCommentId());

        Optional.ofNullable(comment.getContent())
                .ifPresent(findComment::setContent);
        return commentRepository.save(findComment);
    }

    public Comment findComment(Long commentId){
        return findVerifiedComment(commentId);
    }

    public Page<Comment> findComments(String kind, Long id, int page, int size){
        return commentRepository.findAll(PageRequest.of(page,size, Sort.by("commentId").descending()));
    }

    public void deleteComment(long commentId){
        Comment comment = findVerifiedComment(commentId);
        commentRepository.delete(comment);
    }

    private Comment findVerifiedComment(Long commentId){
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment findComment =
                optionalComment.orElseThrow( ()->
                        new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return findComment;
    }
}
