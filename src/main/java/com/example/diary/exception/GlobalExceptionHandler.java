package com.example.diary.exception;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;


//예외처리방식중 전역에서 발생하는 예외들을 받아줄 클래스를 따로 작성했습니다

@RestControllerAdvice
public class GlobalExceptionHandler {
    //패스워드 검증 실패시 해당 예외가 호출됩니다
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handlerBadCredentials(BadCredentialsException ez) {
        return new ResponseEntity<>(ez.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handlerEmptyResultDataAccess(EmptyResultDataAccessException ez) {
        return new ResponseEntity<>("해당 ID로 조회되지 않습니다. 올바른 작성자 ID 및 일정 ID를 입력해주세요.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handlerMethodArgumentNotValid(MethodArgumentNotValidException ez) {
        List<String> errorList = ez.getBindingResult().getFieldErrors().stream().
                map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
}
