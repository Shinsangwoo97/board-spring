package com.board.springboot.board.dto.responseDto;

import com.board.springboot.board.model.Attachment;
import com.board.springboot.board.model.Category;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class MainPageResponseDto {
    private Long boardId;
    private Category category;
    private List<Attachment> attachments;
    private String title;
    private String username;
    private int views;
    private Timestamp registerAt;
    private Timestamp updatedAt;

}
