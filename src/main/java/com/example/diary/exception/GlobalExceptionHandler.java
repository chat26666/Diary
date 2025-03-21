package com.example.diary.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//예외처리방식중 전역에서 발생하는 예외들을 받아줄 클래스를 따로 작성했습니다

@RestControllerAdvice
public class GlobalExceptionHandler {
    //패스워드 검증 실패시 해당 예외가 호출됩니다
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handlerBadCredentials(BadCredentialsException ez) {
        return new ResponseEntity<>(ez.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
