package com.board.springboot.board.service;

import com.board.springboot.board.dto.requestDto.CreateBoardRequestDto;
import com.board.springboot.board.model.Board;
import com.board.springboot.board.model.Category;
import com.board.springboot.board.repository.BoardRepository;
import com.board.springboot.exceptionHandler.CustomException;
import com.board.springboot.exceptionHandler.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity createBoard(CreateBoardRequestDto createBoardRequestDto) {
        Category category = createBoardRequestDto.getCategory();
        String username = createBoardRequestDto.getUsername();
        String password = createBoardRequestDto.getPassword();
        String passwoardCheck = createBoardRequestDto.getPasswordCheck();
        String title = createBoardRequestDto.getTitle();
        String content = createBoardRequestDto.getContent();
        System.out.println("category"+ category);

        // 유효성 검사
        if(!password.equals(passwoardCheck)){
            throw new CustomException(ErrorCode.PASSWORD_CHECK, "");
        }
        password = passwordEncoder.encode(password);
        Board board = new Board(category, username, password, title, content);
        boardRepository.save(board);

        return new ResponseEntity("게시물 생성완료", HttpStatus.OK);
    }
}
