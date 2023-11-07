package com.board.springboot.board.repository;

import com.board.springboot.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findBoardById(Long boardId);
}
