package com.board.springboot.user.dto;

import com.board.springboot.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private Long userId;
    private String username;
    private String nickname;

    public static UserRequestDto fromUser(User user) {

        UserRequestDto requestDto = new UserRequestDto();

        requestDto.userId = user.getId();
        requestDto.username = user.getUsername();
        requestDto.nickname = user.getNickname();

        return requestDto;
    }
}
