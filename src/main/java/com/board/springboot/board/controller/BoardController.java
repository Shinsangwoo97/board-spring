package com.board.springboot.board.controller;

import com.board.springboot.board.dto.requestDto.CreateBoardRequestDto;
import com.board.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardMainService;

    @PostMapping("/api/board/add")
    public ResponseEntity createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto){
        return boardMainService.createBoard(createBoardRequestDto);
    }
}
