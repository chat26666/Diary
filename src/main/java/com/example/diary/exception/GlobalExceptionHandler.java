package com.example.diary.exception;
import org.springframework.dao.DataIntegrityViolationException;
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
        return new ResponseEntity<>("해당 ID로 조회되지 않습니다. 올바른 작성자 ID 및 일정 ID 를 입력해주세요.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handlerMethodArgumentNotValid(MethodArgumentNotValidException ez) {
        List<String> errorList = ez.getBindingResult().getFieldErrors().stream().
                map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        //입력값을 검증합니다
        //만약 입력값이 빠졌거나 잘못된 값이 입력될 경우 해당 에러들을 리스트로 반환합니다
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDataIntegrityViolation(DataIntegrityViolationException ez) {
        return new ResponseEntity<>("등록되지 않은 사용자 ID 입니다.", HttpStatus.BAD_REQUEST);
    } //해당 메서드는 일정 생성 시에 writer 테이블을 외래키로 참조하는 diary 테이블이 writer 테이블에 없는 키로 생성하려 할때 발생하는 예외를 처리해줍니다
}
