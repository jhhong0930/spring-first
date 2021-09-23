package com.sparta.springfirst.service;

import com.sparta.springfirst.domain.Board;
import com.sparta.springfirst.dto.BoardDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService service;

    @Test
    @DisplayName("게시글 추가 테스트")
    void testRegister() {

        BoardDto dto = BoardDto.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .writer("user0")
                .build();

        System.out.println(service.register(dto));
    }

    @Test
    @DisplayName("목록 조회 테스트")
    void testGetList() {
        System.out.println(service.getList());
    }

    @Test
    @DisplayName("게시글 조회 테스트")
    void testRead() {
        System.out.println(service.read(100L));
    }
}