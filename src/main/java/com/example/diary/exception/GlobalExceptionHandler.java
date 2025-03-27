package com.example.diary.exception;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

        /* 예외처리방식 중 전역에서 발생하는 예외들을 처리할 예외 클래스를 작성했습니다 */

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

        /* 클라이언트의 입력값을 검증합니다
           만약 입력값이 빠졌거나 잘못된 값이 입력될 경우 해당 에러들을 리스트로 반환합니다
           typeMismatch 예외의 경우에는 날짜 데이터 타입이나 page 입력시 문자열 같은 데이터가 입력될 경우 여기서 처리됩니다
           해당 예외는 ExceptionHandler 를 따로 생성해서 처리하고 싶었으나 @Valid 를 이용한 @RequestBody 나 @ModelAttribute 들은
           예외 발생시 전부 MethodArgumentNotValidException 예외를 발생시켜서
           부득이하게 이 메서드 안에서 에러코드 내에 typeMismatch 문자가 포함된 것만 분기문 처리하였습니다 */
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDataIntegrityViolation(DataIntegrityViolationException ez) {
        return new ResponseEntity<>("등록되지 않은 사용자 입니다. 사용자를 먼저 등록하십시오.", HttpStatus.BAD_REQUEST);

        /* 해당 메서드는 일정 생성 시에 writer 테이블을 참조하는 diary 테이블이
           writer 테이블에 없는 키로 생성하려 할때 발생하는 예외를 처리해줍니다
           때문에 반드시 일정 생선 전에는 writer 로 등록을 한 후, ID를 발급받아야 합니다 */
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFoundException(UserNotFoundException ez) {
        return new ResponseEntity<>(ez.getMessage(), HttpStatus.BAD_REQUEST);

         //사용자 삭제시 ID, 이메일 , 이름이 틀릴 경우 해당 예외가 반환됩니다
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handlerMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ez) {
        return new ResponseEntity<>("ID 값은 1 이상의 숫자로 입력해주십시오.", HttpStatus.BAD_REQUEST);

        //PathVariable 로 전달되는 인자가 Integer 타입인지 확인합니다
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handlerNoResourceFoundException(NoResourceFoundException ez) {
        return new ResponseEntity<>("잘못된 경로로 접근하셨습니다.", HttpStatus.NOT_FOUND);

        //잘못된 리소스 경로로 접근시 반환됩니다
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ez) {
        return new ResponseEntity<>("해당 HTTP 메소드는 지원하지 않습니다", HttpStatus.METHOD_NOT_ALLOWED);

        //잘못된 웹 메소드로 접근시 반환됩니다
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handlerHttpMessageNotReadableException(HttpMessageNotReadableException ez) {
        return new ResponseEntity<>("요청 본문에 전달된 JSON 데이터의 형식이 올바르지 않아, 요청을 처리할 수 없습니다. 올바른 JSON 형식으로 재전송해 주시기 바랍니다.", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ez) {
        return new ResponseEntity<>("지원하지 않는 미디어 타입입니다",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining("\n"));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        //ID 값이 1 미만으로 입력될 경우 반환됩니다
    }
}
