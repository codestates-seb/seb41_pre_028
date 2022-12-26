package com.codestates.pre_028.stackoverflow_clone.comment.service;

import com.codestates.pre_028.stackoverflow_clone.comment.entity.Comment;
import com.codestates.pre_028.stackoverflow_clone.comment.repository.CommentRepository;
import com.codestates.pre_028.stackoverflow_clone.exception.BusinessLogicException;
import com.codestates.pre_028.stackoverflow_clone.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }


    public Comment createComment(Comment comment){
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
