package com.example.diary.exception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Arrays;
import java.util.List;

        //예외처리방식 중 전역에서 발생하는 예외들을 처리할 예외 클래스를 작성했습니다
        //가능한 발생할 수 있는 모든 예외를 처리하려고 노력했고 클라이언트한테는 구체적인 에러메시지가
        //아닌 짧고 간단한 메시지만 반환하여 보안적인 측면을 강화했습니다

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handlerBadCredentials(BadCredentialsException ez) {
        return new ResponseEntity<>(ez.getMessage(), HttpStatus.UNAUTHORIZED);

        //패스워드 검증 실패시 해당 예외가 호출됩니다
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handlerEmptyResultDataAccess(EmptyResultDataAccessException ez) {
        return new ResponseEntity<>("해당 ID가 조회되지 않습니다. 올바른 작성자 ID 및 일정 ID 를 입력해주세요.", HttpStatus.NOT_FOUND);

        //잘못된 ID로 조회시 해당 예외가 호출됩니다
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handlerMethodArgumentNotValid(MethodArgumentNotValidException ez) {
        List<String> errorList = ez.getBindingResult().getFieldErrors().stream().
                map(error -> {
                    if (error.getCodes() != null && Arrays.stream(error.getCodes()).anyMatch(code -> "typeMismatch".equals(code))) {
                        return error.getField() + " : 입력된 데이터 형식이 올바르지 않습니다.";
                    }
                    return error.getField() + " : " + error.getDefaultMessage();
                })
                .toList();
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);

        //클라이언트의 입력값을 검증합니다
        //만약 입력값이 빠졌거나 잘못된 값이 입력될 경우 해당 에러들을 리스트로 반환합니다
        //typeMismatch 예외의 경우에는 날짜 데이터 타입이나 page 입력시 문자열같은 데이터가 입력될 경우 여기서 처리됩니다
        //해당 예외는 따로 ExceptionHandler 를 생성해서 처리하고 싶었으나
        //발생시 전부 MethodArgumentNotValidException 예외를 발생시켜서 부득이하게 이 메서드 안에서 에러코드 내에 typeMismatch 가 포함된 것만 분기문 처리하였습니다
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDataIntegrityViolation(DataIntegrityViolationException ez) {
        return new ResponseEntity<>("등록되지 않은 사용자 입니다. 사용자를 먼저 등록하십시오.", HttpStatus.BAD_REQUEST);

        //해당 메서드는 일정 생성 시에 writer 테이블을 참조하는 diary 테이블이 writer 테이블에 없는 키로 생성하려 할때 발생하는 예외를 처리해줍니다
        //때문에 반드시 일정 생선 전에는 writer 로 등록을 한 후, ID를 발급받아야 합니다
    }
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<String> handlerBadSqlGrammarException(BadSqlGrammarException ez) {
        return new ResponseEntity<>("페이지 및 사이즈를 제대로 입력해주십시오", HttpStatus.BAD_REQUEST);

        //페이지와 사이즈를 입력시 음수로 입력했을 경우, 해당 예외가 호출됩니다
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFoundException(UserNotFoundException ez) {
        return new ResponseEntity<>(ez.getMessage(), HttpStatus.BAD_REQUEST);

        //사용자 삭제시 ID, 이메일 , 이름이 틀릴 경우 해당 예외가 반환됩니다
    }
}
