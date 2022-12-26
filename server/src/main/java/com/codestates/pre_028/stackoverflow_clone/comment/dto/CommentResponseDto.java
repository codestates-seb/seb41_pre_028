package com.codestates.pre_028.stackoverflow_clone.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponseDto {
    private long userId;
    private String content;
}
