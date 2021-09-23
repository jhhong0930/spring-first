package com.sparta.springfirst.service;

import com.sparta.springfirst.domain.Board;
import com.sparta.springfirst.domain.BoardRepository;
import com.sparta.springfirst.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Board> getList() {

        return repository.findAll(Sort.by(Sort.Direction.DESC, "regDate"));
    }

    @Override
    public BoardDto read(Long id) {

        Optional<Board> result = repository.findById(id);

        return result.map(this::entityToDto).orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다."));
    }
}
