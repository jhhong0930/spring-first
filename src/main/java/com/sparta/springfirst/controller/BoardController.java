package com.sparta.springfirst.controller;

import com.sparta.springfirst.dto.BoardDto;
import com.sparta.springfirst.dto.PageRequestDto;
import com.sparta.springfirst.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService service;

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/boards/list";
    }

    /**
     * 목록 화면
     */
    @GetMapping("/boards/list")
    public String getList(PageRequestDto pageRequestDto, Model model) {

        log.info("list = {}", pageRequestDto);

        model.addAttribute("result", service.getList(pageRequestDto));

        return "./boards/list.html";
    }

    /**
     * 새 게시글 등록 폼
     */
    @GetMapping("/boards/new")
    public String register() {
        return "boards/register";
    }

    /**
     * 게시글 등록
     * @param dto
     */
    @PostMapping("/boards/new")
    public String register(BoardDto dto, RedirectAttributes rttr) {

        log.info("dto = {}", dto);

        Long id = service.register(dto);

        rttr.addFlashAttribute("msg", id);

        return "redirect:/boards/list";
    }

    /**
     * 게시글 조회
     * @param id
     */
    @GetMapping("/boards/{id}")
    public String read(@PathVariable Long id, Model model) {

        log.info("id = {}", id);

        BoardDto dto = service.read(id);

        model.addAttribute("dto", dto);

        return "./boards/read.html";
    }

    /**
     * 게시글 수정 폼
     */
    @GetMapping("/boards/{id}/edit")
    public String update(@PathVariable Long id, Model model) {

        log.info("id = {}", id);

        BoardDto dto = service.read(id);

        model.addAttribute("dto", dto);

        return "./boards/update.html";
    }

    /**
     * 게시글 수정
     * @param dto
     */
    @PostMapping("/boards/edit")
    public String update(BoardDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto, RedirectAttributes rttr) {

        log.info("dto = {}", dto);

        service.update(dto);

        Long id = dto.getId();

        return "redirect:/boards/" + id;
    }

    /**
     * 게시글 삭제
     * @param id
     */
    @PostMapping("/boards/{id}/delete")
    public String delete(@PathVariable Long id) {

        log.info("id = {}", id);

        service.delete(id);

        return "redirect:/boards/list";
    }
}
