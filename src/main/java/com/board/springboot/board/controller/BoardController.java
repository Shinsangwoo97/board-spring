package com.board.springboot.board.controller;

import com.board.springboot.board.dto.requestDto.CreateBoardRequestDto;
import com.board.springboot.board.dto.responseDto.BoardListResponseDto;
import com.board.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardMainService;

    @PostMapping("/api/board/add")
    public ResponseEntity createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto){
        return boardMainService.createBoard(createBoardRequestDto);
    }
    @GetMapping("/api/board/list")
    public List<BoardListResponseDto> getBoardList(){
        return boardMainService.getBoardList();
    }

    @PatchMapping("/api/board/list/{id}")
    public ResponseEntity updateBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto,
                                      @PathVariable Long id){
        return boardMainService.updateBoard(createBoardRequestDto, id);
    }
    @DeleteMapping("/api/board/list/delete/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id,
                                      @RequestBody CreateBoardRequestDto createBoardRequestDto){
        return boardMainService.deleteBoard(createBoardRequestDto, id);
    }

}
