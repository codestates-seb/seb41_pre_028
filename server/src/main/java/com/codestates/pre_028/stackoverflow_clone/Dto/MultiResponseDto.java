package com.codestates.pre_028.stackoverflow_clone.Dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
public class MultiResponseDto<T> {
    private List<T> data;
//    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data) {
        this.data = data;
//        this.pageInfo = new PageInfo(page.getNumber() + 1,
//                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
