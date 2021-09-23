package com.sparta.springfirst.service;

import com.sparta.springfirst.domain.Board;
import com.sparta.springfirst.dto.BoardDto;
import com.sparta.springfirst.dto.PageRequestDto;
import com.sparta.springfirst.dto.PageResultDto;
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

        PageRequestDto pageRequestDTO = PageRequestDto.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDto<BoardDto, Board> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("====================================");
        for (BoardDto boardDto : resultDTO.getDtoList()) {
            System.out.println(boardDto);
        }

        System.out.println("====================================");
        resultDTO.getPageList().forEach(System.out::println);
    }

    @Test
    @DisplayName("게시글 조회 테스트")
    void testRead() {
        System.out.println(service.read(100L));
    }
}