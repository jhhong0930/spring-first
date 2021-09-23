package com.sparta.springfirst.service;

import com.querydsl.core.BooleanBuilder;
import com.sparta.springfirst.domain.Board;
import com.sparta.springfirst.domain.BoardRepository;
import com.sparta.springfirst.dto.BoardDto;
import com.sparta.springfirst.dto.PageRequestDto;
import com.sparta.springfirst.dto.PageResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;

    @Override
    public Long register(BoardDto dto) {

        log.info("dto = {}", dto);

        Board entity = dtoToEntity(dto);

        log.info("entity = {}", entity);

        repository.save(entity);

        return entity.getId();
    }

    @Override
    public PageResultDto<BoardDto, Board> getList(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("regDate").descending());

        Page<Board> result = repository.findAll(pageable);

        Function<Board, BoardDto> fn = (this::entityToDto);

        return new PageResultDto<>(result, fn);
    }

    @Override
    public BoardDto read(Long id) {

        Optional<Board> result = repository.findById(id);

        return result.map(this::entityToDto).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다."));
    }

    @Override
    public void update(BoardDto dto) {

        Optional<Board> result = repository.findById(dto.getId());

        if (result.isPresent()) {

            Board entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }
}
