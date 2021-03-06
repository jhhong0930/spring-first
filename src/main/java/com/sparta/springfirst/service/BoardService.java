package com.sparta.springfirst.service;

import com.sparta.springfirst.domain.Board;
import com.sparta.springfirst.dto.BoardDto;
import com.sparta.springfirst.dto.PageRequestDto;
import com.sparta.springfirst.dto.PageResultDto;

import java.util.List;

public interface BoardService {

    // 게시글 추가
    Long register(BoardDto dto);

    // 게시글 목록 조회(페이징 O)
    PageResultDto<BoardDto, Board> getList(PageRequestDto requestDto);

    // 게시글 조회
    BoardDto read(Long id);

    // 게시글 수정
    void update(BoardDto dto);

    // 게시글 삭제
    void delete(Long id);

    default BoardDto entityToDto(Board entity) {

        BoardDto dto = BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }

    default Board dtoToEntity(BoardDto dto) {

        Board entity = Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

}
