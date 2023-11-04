package com.board.springboot.board.dto.requestDto;

import com.board.springboot.board.model.Category;
import lombok.Getter;

@Getter
public class CreateBoardRequestDto {
    private Category category;
    private String username;
    private String password;
    private String passwordCheck;
    private String title;
    private String content;
}
