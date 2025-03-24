package com.example.diary.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String message){
        super(message);
    }
}

//커스텀 예외 클래스입니다 사용자 삭제시 입력값이 틀릴시 호출됩니다