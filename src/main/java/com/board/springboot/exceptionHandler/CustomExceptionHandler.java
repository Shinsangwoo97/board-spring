package com.board.springboot.exceptionHandler;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return "올바르지 않은 JSON 데이터입니다. 유효한 Enum 값을 입력해주세요.";
    }
}
