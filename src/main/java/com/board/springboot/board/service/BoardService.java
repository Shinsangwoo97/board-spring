package com.board.springboot.board.service;

import com.board.springboot.board.dto.requestDto.CreateBoardRequestDto;
import com.board.springboot.board.dto.responseDto.BoardListResponseDto;
import com.board.springboot.board.model.Board;
import com.board.springboot.board.model.Category;
import com.board.springboot.board.repository.BoardRepository;
import com.board.springboot.exceptionHandler.CustomException;
import com.board.springboot.exceptionHandler.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.board.springboot.board.model.Category.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
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
        // TODO: (게시물 생성) enum추가 될떄마다 여기 추가해줘야하니 category 수정시 신경안써도되게 바꿔줘야함!!
        if(!(category == JAVA || category == JAVASCRIPT || category == DATABASE)){
            throw  new CustomException(ErrorCode.CATEGORY_NOT_FOUND,"");
        }
        password = passwordEncoder.encode(password);
        System.out.println("비밀번호"+password);
        Board board = new Board(category, username, password, title, content);
        boardRepository.save(board);

        return new ResponseEntity("게시물 생성완료", HttpStatus.OK);
    }
    @Transactional
    public List<BoardListResponseDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardListResponseDto> boardListResponseDtos = new ArrayList<>();
        for (Board board : boardList){
            BoardListResponseDto boardListResponseDto = new BoardListResponseDto(board);
            boardListResponseDtos.add(boardListResponseDto);
        }
        return boardListResponseDtos;
    }

    // TODO: 유효성검사 빠짐
    @Transactional
    public ResponseEntity updateBoard(CreateBoardRequestDto createBoardRequestDto, Long id) {
        String username = createBoardRequestDto.getUsername();
        String password = createBoardRequestDto.getPassword();
        String title = createBoardRequestDto.getTitle();
        String conent = createBoardRequestDto.getContent();
//        Board board = boardRepository.getReferenceById(id);
//        if(board.getTitle().isEmpty()){
//            throw new CustomException(ErrorCode.BOARD_NOT_FOUND, "");
//        }
//        Optional<Board> patchboard = boardRepository.findById(id);
//        if(patchboard.isEmpty()){
//            throw new CustomException(ErrorCode.BOARD_NOT_FOUND, "");
//        }
        password = passwordEncoder.encode(password);
        Board patchboard = boardRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.BOARD_NOT_FOUND, ""));
        patchboard.setUsername(username);
        patchboard.setPassword(password);
        patchboard.setTitle(title);
        patchboard.setContent(conent);
        boardRepository.save(patchboard);
        return new ResponseEntity("게시물 수정완료", HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity deleteBoard(CreateBoardRequestDto createBoardRequestDto, Long id) {
        String password = createBoardRequestDto.getPassword();
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.BOARD_NOT_FOUND, ""));
        System.out.println("저장된비밀번호"+board.getPassword());
        if(!(board.getUsername().equals(createBoardRequestDto.getUsername()))){
            throw  new CustomException(ErrorCode.USER_NAME_UNAUTHORIZED,"");
        }
        if(!(passwordEncoder.matches(password, board.getPassword()))){
            throw  new CustomException(ErrorCode.USER_PASSWORD_UNAUTHORIZED,"");
        }
        boardRepository.deleteById(id);
        return ResponseEntity.ok("게시글 삭제 완료");
    }
}
