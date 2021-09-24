package com.sparta.springfirst.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository repository;

//    @Test
    @DisplayName("셈플 데이터 추가")
    void testRegister() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            Board board = Board.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .writer("user" + (i % 10))
                    .build();

            System.out.println(repository.save(board));
        });
    }

//    @Test
    @DisplayName("수정 시간 테스트")
    void testUpdate() {

        Optional<Board> result = repository.findById(200L);

        if (result.isPresent()) {

            Board board = result.get();

            board.changeTitle("변경된 제목");
            board.changeContent("변경된 내용");

            repository.save(board);
        }
    }

}