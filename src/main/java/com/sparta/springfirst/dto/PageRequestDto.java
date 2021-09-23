package com.sparta.springfirst.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {

    private int page;
    private int size;
    private String type; // 검색 타입(조건)
    private String keyword; // 검색 키워드

    public PageRequestDto() {
        this.page = 1;  // 페이지 번호
        this.size = 10; // 10개씩 처리
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }

}
