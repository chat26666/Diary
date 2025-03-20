package com.example.diary.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreateUserDto {
    Integer writerId;

    public ResponseCreateUserDto(Integer writerId) {
        this.writerId = writerId;
    }
}
