package com.board.springboot.board.dto.responseDto;

import com.board.springboot.board.model.Board;
import com.board.springboot.board.model.Category;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class BoardListResponseDto {
    private Long id;
    private Category category;
    private String title;
    private String username;
    private int views;
    private Timestamp registerAt;
    private Timestamp updatedAt;

    public BoardListResponseDto(Board board){
        this.id = board.getId();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.views = board.getViews();
        this.registerAt = board.getRegisterAt();
        this.updatedAt = board.getUpdatedAt();
    }
}
