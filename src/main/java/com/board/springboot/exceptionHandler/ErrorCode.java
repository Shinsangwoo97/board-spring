package com.board.springboot.exceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카테고리를 찾을 수 없습니다"),
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "만료되었거나 유효하지 않은 토큰입니다."),
    AUTH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다"),
    USER_PASSWORD_NOT_FOUND(HttpStatus.NOT_FOUND, "유저의 아이디,비밀번호를 다시 확인해주세요."),
    PASSWORD_CHECK(HttpStatus.CONFLICT, "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
